
package com.ctosb.codebuild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctosb.codebuild.entity.Config;
import com.ctosb.codebuild.entity.ConfigItem;
import com.ctosb.codebuild.service.ConfigService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "配置接口")
@RequestMapping("config")
public class ConfigController {

	@Autowired
	private ConfigService configService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "新增配置")
	public Config save(@ApiParam(required = true, name = "config", value = "配置信息") @ModelAttribute Config config) {
		return configService.save(config);
	}

	@RequestMapping(value = "/{configId}", method = RequestMethod.GET)
	@ApiOperation(value = "通过id，获取配置信息")
	public Config getById(
			@ApiParam(required = true, name = "configId", value = "配置id") @PathVariable("configId") String configId) {
		return configService.getById(configId);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "通过名称，查询配置")
	public List<ConfigItem> getByConfigName(
			@ApiParam(required = false, name = "configName", value = "配置名称") @RequestParam("configName") String configName) {
		return configService.getConfigItemByConfigName("%" + configName + "%");
	}

	@RequestMapping(value = "/datasource", method = RequestMethod.POST)
	@ApiOperation(value = "添加数据源")
	public int addDatasource(
			@ApiParam(required = true, name = "configId", value = "配置Id") @RequestParam("configId") String configId,
			@ApiParam(required = true, name = "datasourceId", value = "数据源Id") @RequestParam("datasourceId") String datasourceId) {
		return configService.updateDatasourceIdByConfigId(datasourceId, configId);
	}
}
