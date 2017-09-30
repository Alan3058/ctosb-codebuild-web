
package com.ctosb.codebuild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ctosb.codebuild.entity.Datasource;
import com.ctosb.codebuild.service.DatasourceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "数据源接口")
@RequestMapping("datasource")
public class DatasourceController {

	@Autowired
	private DatasourceService datasourceService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "新增数据源")
	public Datasource save(
			@ApiParam(required = true, name = "datasource", value = "数据源信息") @ModelAttribute Datasource datasource) {
		return datasourceService.save(datasource);
	}

	@RequestMapping(value = "/{datasourceId}", method = RequestMethod.GET)
	@ApiOperation(value = "通过id，获取数据源信息")
	public Datasource getByDataSourceId(
			@ApiParam(required = true, name = "datasourceId", value = "数据源id") @PathVariable("datasourceId") String datasourceId) {
		return datasourceService.getByDataSourceId(datasourceId);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ApiOperation(value = "获取数据源信息")
	public List<Datasource> getAll() {
		return datasourceService.getAll();
	}
}
