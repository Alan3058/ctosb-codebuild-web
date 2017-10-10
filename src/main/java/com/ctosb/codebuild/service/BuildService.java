/*
 * @Project Name: springboot-demo
 * @File Name: UserService.java
 * @Package Name: com.ctosb.springboot.service
 * @Date: 2017年9月1日下午1:02:06
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.ctosb.codebuild.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctosb.codebuild.dao.DatasourceRepository;
import com.ctosb.codebuild.dao.TemplateRepository;
import com.ctosb.codebuild.entity.Config;
import com.ctosb.codebuild.entity.Datasource;
import com.ctosb.codebuild.entity.Template;
import com.ctosb.codebuild.exception.ProcessException;
import com.ctosb.codebuild.model.BuildInfo;
import com.ctosb.codebuild.model.ColumnInfo;
import com.ctosb.codebuild.model.GenerateInfo;
import com.ctosb.codebuild.model.TableInfo;
import com.ctosb.codebuild.util.CamelCaseUtil;
import com.ctosb.codebuild.util.DbUtil;
import com.ctosb.codebuild.util.EmptyUtil;
import com.ctosb.codebuild.util.TypeMappingUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * 配置服务
 * @author liliangang-1163
 * @date 2017年9月20日下午1:33:49
 */
@Component
public class BuildService {

	@Autowired
	private ConfigService configService;
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private TemplateRepository templateRepository;

	/**
	 * 通过数据源id和表名获取表信息
	 * @date 2017年9月30日下午1:20:44
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param datasourceId
	 * @param tableName
	 * @return
	 */
	public Collection<TableInfo> getTableInfoByTableName(String datasourceId, String tableName) {
		Datasource datasource = datasourceRepository.findOne(datasourceId);
		return getTableInfoByTableName(getConnection(datasource), tableName, false);
	}

	/**
	 * 通过数据库连接和表名获取表信息
	 * @date 2017年9月30日下午1:41:52
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param connection
	 * @param tableName
	 * @param withLoadColumn
	 * @return
	 */
	public Collection<TableInfo> getTableInfoByTableName(Connection connection, String tableName,
			boolean withLoadColumn) {
		DatabaseMetaData dbmd = getDatabaseMetaData(connection);
		if (dbmd == null) {
			return null;
		}
		Collection<TableInfo> tableInfos = new ArrayList<TableInfo>();
		try {
			// 获取表信息
			ResultSet rs = dbmd.getTables(null, null, tableName, null);
			while (rs.next()) {
				TableInfo tableInfo = new TableInfo();
				tableInfo.setTabName(rs.getString("TABLE_NAME"));// 表名
				tableInfo.setTabComment(rs.getString("REMARKS"));// 表备注
				tableInfo.setTabType(rs.getString("TABLE_TYPE"));// 表类型
				tableInfo.setUpperCamelTabName(CamelCaseUtil.toUpperCamelCase(tableInfo.getTabName()));
				tableInfo.setCamelTabName(CamelCaseUtil.toCamelCase(tableInfo.getTabName()));
				if (withLoadColumn) {
					// 加载列信息
					tableInfo.setColumns(getColumnInfoByTableName(connection, tableName));
				}
				tableInfos.add(tableInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableInfos;
	}

	/**
	 * 生成数据库表的构建信息
	 * @date 2017年9月30日上午10:42:33
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param configId
	 * @param tableName
	 * @return
	 */
	public Collection<GenerateInfo> buildByConfigIdAndTableName(String configId, String tableName) {
		Config config = configService.getById(configId);
		Datasource datasource = datasourceRepository.findOne(config.getDatasourceId());
		List<Template> templates = templateRepository.findByConfigId(config.getConfigId());
		Collection<TableInfo> tableInfos = getTableInfoByTableName(getConnection(datasource), tableName, true);
		List<GenerateInfo> generateInfos = new ArrayList<>();
		for (TableInfo tableInfo : tableInfos) {
			BuildInfo buildInfo = new BuildInfo();
			buildInfo.setTableInfo(tableInfo);
			buildInfo.setColumnInfos(tableInfo.getColumns());
			for (Template template : templates) {
				buildInfo.setPackageName(template.getGeneratePackagePath().replaceAll("[/\\\\]", "."));
				GenerateInfo generateInfo = new GenerateInfo();
				generateInfo.setFileName(tableInfo.getUpperCamelTabName() + template.getGenerateSuffix());
				generateInfo.setSourceFolder(template.getGenerateSourceFolder());
				generateInfo.setPackagePath(template.getGeneratePackagePath().replace(".", File.separator));
				generateInfo.setContent(generate(template.getTemplateContent(), buildInfo));
				generateInfos.add(generateInfo);
			}
		}
		return generateInfos;
	}

	/**
	 * 测试数据库连接
	 * @date 2017年9月29日下午12:37:44
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param datasourceId
	 * @return
	 */
	public boolean testConnection(String datasourceId) {
		Datasource datasource = datasourceRepository.findOne(datasourceId);
		Connection connection = getConnection(datasource);
		return connection != null;
	}

	/**
	 * 测试数据库连接
	 * @date 2017年9月29日下午12:37:54
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param datasource
	 * @return
	 */
	public boolean testConnection(Datasource datasource) {
		Connection connection = getConnection(datasource);
		return connection != null;
	}

	/**
	 * 通过数据源Id，获取表名的列信息
	 * @date 2017年9月28日下午1:11:53
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param datasourceId
	 * @param tableName
	 * @return
	 */
	public Collection<ColumnInfo> getColumnInfoByTableName(String datasourceId, String tableName) {
		Datasource datasource = datasourceRepository.findOne(datasourceId);
		return getColumnInfoByTableName(getConnection(datasource), tableName);
	}

	/**
	 * 通过数据库连接，获取数据库表的列信息
	 * @date 2017年9月28日下午1:11:53
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param connection
	 * @param tableName
	 * @return
	 */
	public Collection<ColumnInfo> getColumnInfoByTableName(Connection connection, String tableName) {
		DatabaseMetaData dbmd = getDatabaseMetaData(connection);
		if (dbmd == null) {
			return null;
		}
		Collection<ColumnInfo> cols = new ArrayList<ColumnInfo>();
		Collection<String> primaryKeys = getPrimaryKeyNames(dbmd, tableName);// 获取主键列名
		try {
			ResultSet rs = dbmd.getColumns(null, null, tableName, null);// 获取表的信息
			while (rs.next()) {
				String columnName = rs.getString("COLUMN_NAME");// 列名
				String columnType = rs.getString("TYPE_NAME");// 列类型
				int nullable = rs.getInt("NULLABLE");
				String columnRemark = rs.getString("REMARKS");// 列注释
				// 判断备注是否为空，为空就用字段名
				if (EmptyUtil.isEmpty(columnRemark)) {
					columnRemark = columnName;
				}
				// 装配列对象
				ColumnInfo col = new ColumnInfo();
				col.setColName(columnName);
				col.setColType(columnType);
				col.setJavaColType(TypeMappingUtil.getType(columnType.toUpperCase()));
				col.setUpperCamelColName(CamelCaseUtil.toUpperCamelCase(columnName));
				col.setCamelColName(CamelCaseUtil.toCamelCase(columnName));
				col.setNullable(nullable == 1);// 是否可为空
				col.setColComment(columnRemark);// 设置备注
				col.setPrimary(primaryKeys.contains(columnName)); // 判断是否主键
				cols.add(col);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cols;
	}

	/**
	 * 获取主键名称
	 * @date 2017年9月30日上午10:45:03
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param dbmd
	 * @param tableName
	 * @return
	 */
	private Collection<String> getPrimaryKeyNames(DatabaseMetaData dbmd, String tableName) {
		Collection<String> primaryKeys = new ArrayList<String>();
		try {
			ResultSet rsPrimary = dbmd.getPrimaryKeys(null, null, tableName);
			while (rsPrimary.next()) {
				primaryKeys.add(rsPrimary.getString("COLUMN_NAME"));// 列名
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return primaryKeys;
	}

	/**
	 * 获取数据库信息
	 * @param conn
	 * @return
	 * @author Alan
	 * @time 2015-11-11 上午09:53:43
	 */
	private DatabaseMetaData getDatabaseMetaData(Connection conn) {
		if (conn == null) {
			return null;
		}
		try {
			return conn.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化jdbc连接驱动
	 * @return
	 * @author Alan
	 * @time 2015-11-10 下午06:03:08
	 */
	private Connection getConnection(Datasource datasource) {
		// 校验dataSource是否为空
		if (datasource == null) {
			throw new ProcessException("The dataSource is null");
		}
		return DbUtil.getConnection(datasource.getJdbcDriver(), datasource.getJdbcUrl(), datasource.getJdbcUserName(),
				datasource.getJdbcPassword());
	}

	/**
	 * freemarker模版编译
	 * @date 2017年9月29日下午1:26:13
	 * @author liliangang-1163
	 * @since 1.0.0
	 * @param content
	 * @param obj
	 * @return
	 */
	private String generate(String content, Object obj) {
		Configuration config = new Configuration(Configuration.VERSION_2_3_26);
		// 设置处理空值
		config.setClassicCompatible(true);
		// 设置对象包装器
		config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_26));
		// 设置异常处理器
		config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
		try {
			freemarker.template.Template template = new freemarker.template.Template(null, content, config);
			StringWriter stringWriter = new StringWriter();
			template.process(obj, stringWriter);
			return stringWriter.toString();
		} catch (IOException | TemplateException e) {
			e.printStackTrace();
		}
		return "";
	}
}
