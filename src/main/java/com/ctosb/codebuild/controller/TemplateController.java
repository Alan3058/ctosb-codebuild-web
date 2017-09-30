
package com.ctosb.codebuild.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ctosb.codebuild.entity.Template;
import com.ctosb.codebuild.service.TemplateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = "模版接口")
@RequestMapping("/template")
public class TemplateController {

	@Autowired
	private TemplateService templateService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation(value = "新增模版")
	public Object save(@ApiParam(required = true, name = "config", value = "模版信息") @ModelAttribute Template template) {
		return templateService.save(template);
	}

	@RequestMapping(value = "/{templateId}", method = RequestMethod.GET)
	@ApiOperation(value = "通过模版id，查询模版")
	public Template getByTemplateId(
			@ApiParam(required = true, name = "templateId", value = "模版id") @PathVariable("templateId") String templateId) {
		return templateService.findByTemplateId(templateId);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(value = "通过配置id，查询模版")
	public List<Template> getByConfigId(
			@ApiParam(required = true, name = "configId", value = "配置Id") @RequestParam("configId") String configId) {
		return templateService.findByConfigId(configId);
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ApiOperation(value = "查询模版")
	public List<Template> getAll() {
		return templateService.getAll();
	}
}
