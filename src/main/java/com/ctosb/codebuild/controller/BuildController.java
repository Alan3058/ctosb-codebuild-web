
package com.ctosb.codebuild.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctosb.codebuild.entity.Datasource;
import com.ctosb.codebuild.model.GenerateInfo;
import com.ctosb.codebuild.model.TableInfo;
import com.ctosb.codebuild.service.BuildService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "构建接口")
@RequestMapping("build")
public class BuildController {

	@Autowired
	private BuildService buildService;

	@RequestMapping(value = "/tableInfo", method = RequestMethod.GET)
	@ApiOperation(value = "通过数据源id，获取表信息")
	public Collection<TableInfo> getTableInfoByTableName(
			@ApiParam(required = true, name = "datasourceId", value = "数据源id") @RequestParam("datasourceId") String datasourceId) {
		return buildService.getTableInfoByTableName(datasourceId, null);
	}

	@RequestMapping(value = "/preview/table", method = RequestMethod.GET)
	@ApiOperation(value = "通过表名，预览生成的源代码")
	public Collection<GenerateInfo> buildByConfigIdAndTableName(
			@ApiParam(required = true, name = "configId", value = "配置id") @RequestParam("configId") String configId,
			@ApiParam(required = true, name = "tableName", value = "表名") @RequestParam("tableName") String tableName) {
		return buildService.buildByConfigIdAndTableName(configId, tableName);
	}

	@RequestMapping(value = "/download/table", method = RequestMethod.GET)
	@ApiOperation(value = "下载生成的源代码压缩包")
	public void downloadBuildByConfigIdAndTableName(
			@ApiParam(required = true, name = "configId", value = "配置id") @RequestParam("configId") String configId,
			@ApiParam(required = true, name = "tableName", value = "表名") @RequestParam("tableName") String tableName,
			HttpServletResponse response) throws IOException {
		Collection<GenerateInfo> generateInfos = buildService.buildByConfigIdAndTableName(configId, tableName);
		download(generateInfos, response);
	}

	@RequestMapping(value = "/download/tables", method = RequestMethod.GET)
	@ApiOperation(value = "批量下载生成的源代码压缩包")
	public void downloadBuildByConfigIdAndTableNames(
			@ApiParam(required = true, name = "configId", value = "配置id") @RequestParam("configId") String configId,
			@ApiParam(required = true, name = "tableName", value = "表名") @RequestParam("tableNames[]") String[] tableNames,
			HttpServletResponse response) throws IOException {
		Collection<GenerateInfo> generateInfos = new ArrayList<GenerateInfo>();
		for (String tableName : tableNames) {
			generateInfos.addAll(buildService.buildByConfigIdAndTableName(configId, tableName));
		}
		download(generateInfos, response);
	}

	/**
	 * 下载生成源码文件
	 * @date 2017年10月4日上午10:37:07
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param generateInfos
	 * @param response
	 * @throws IOException
	 */
	private void download(Collection<GenerateInfo> generateInfos, HttpServletResponse response) throws IOException {
		File tmpFolder = new File("tmp");
		FileUtils.forceMkdir(tmpFolder);
		File zip = new File(tmpFolder, "src.zip");
		ZipArchiveOutputStream archiveOutputStream = new ZipArchiveOutputStream(zip);
		for (GenerateInfo generateInfo : generateInfos) {
			String directory = generateInfo.getSourceFolder() + File.separator + generateInfo.getPackagePath()
					+ File.separator;
			File folder = new File(tmpFolder, directory);
			File file = new File(folder, generateInfo.getFileName());
			if (!folder.exists()) {
				folder.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			FileUtils.write(file, generateInfo.getContent(), "utf-8");
			ZipArchiveEntry entry = new ZipArchiveEntry(file, directory + file.getName());
			archiveOutputStream.putArchiveEntry(entry);
			archiveOutputStream.write(FileUtils.readFileToByteArray(file));
			archiveOutputStream.closeArchiveEntry();
		}
		archiveOutputStream.finish();
		archiveOutputStream.close();
		String fileName = "src.zip";
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		outputStream.write(FileUtils.readFileToByteArray(zip));
		outputStream.flush();
		outputStream.close();
		FileUtils.forceDelete(tmpFolder);
	}

	@RequestMapping(value = "/test/connection", method = RequestMethod.GET)
	@ApiOperation(value = "通过数据源id，测试数据库连接")
	public boolean testConnection(
			@ApiParam(required = true, name = "datasourceId", value = "数据源id") @RequestParam("datasourceId") String datasourceId) {
		return buildService.testConnection(datasourceId);
	}

	@RequestMapping(value = "/test/connection/datasource", method = RequestMethod.GET)
	@ApiOperation(value = "通过数据库信息，测试数据库连接")
	public boolean testConnection(
			@ApiParam(required = true, name = "datasource", value = "数据源") Datasource datasource) {
		return buildService.testConnection(datasource);
	}
}
