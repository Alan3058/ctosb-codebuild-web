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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctosb.codebuild.dao.TemplateRepository;
import com.ctosb.codebuild.entity.Template;

/**
 * 模版服务
 * @author liliangang-1163
 * @date 2017年9月20日下午1:34:03
 */
@Component
public class TemplateService {

	@Autowired
	private TemplateRepository templateRepository;

	public Template save(Template template) {
		return templateRepository.save(template);
	}

	public List<Template> findByConfigId(String configId) {
		return templateRepository.findByConfigId(configId);
	}

	public Template findByTemplateId(String templateId) {
		return templateRepository.findOne(templateId);
	}

	public List<Template> getAll() {
		return templateRepository.findAll();
	}
}
