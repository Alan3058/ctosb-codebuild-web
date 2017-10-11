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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ctosb.codebuild.dao.ConfigRepository;
import com.ctosb.codebuild.dao.DatasourceRepository;
import com.ctosb.codebuild.dao.TemplateRepository;
import com.ctosb.codebuild.entity.Config;
import com.ctosb.codebuild.entity.ConfigItem;
import com.ctosb.codebuild.entity.Datasource;

/**
 * 配置服务
 * @author liliangang-1163
 * @date 2017年9月20日下午1:33:49
 */
@Component
public class ConfigService {

	@Autowired
	private ConfigRepository configRepository;
	@Autowired
	private DatasourceRepository datasourceRepository;
	@Autowired
	private TemplateRepository templateRepository;

	public Config getById(String configId) {
		return configRepository.findOne(configId);
	}

	public List<ConfigItem> getConfigItemByConfigName(String name) {
		// 获取配置信息
		List<Config> configs = configRepository.getByConfigNameLike(name);
		List<ConfigItem> configItems = new ArrayList<ConfigItem>();
		if (CollectionUtils.isEmpty(configs)) {
			return configItems;
		}
		// 提取数据源id
		List<String> datasourceIds = configs.stream().filter(e -> e.getDatasourceId() != null)
				.map(e -> e.getDatasourceId()).collect(Collectors.toList());
		// 查找数据源
		List<Datasource> datasources = datasourceRepository.findByDatasourceIdIn(datasourceIds);
		// 拼装配置信息ConfigItem
		configs.stream().forEach(e -> {
			ConfigItem configItem = new ConfigItem();
			BeanUtils.copyProperties(e, configItem);
			configItem.setDatasource(
					datasources.stream().filter(datasource -> datasource.getDatasourceId().equals(e.getDatasourceId()))
							.findFirst().orElse(null));
			configItem.setTemplates(templateRepository.findByConfigId(e.getConfigId()));
			configItems.add(configItem);
		});
		return configItems;
	}

	public Config save(Config config) {
		return configRepository.save(config);
	}

	@Transactional
	public int updateDatasourceIdByConfigId(String datasourceId, String configId) {
		return configRepository.updateDatasourceIdByConfigId(datasourceId, configId);
	}

	@Transactional
	public int updateConfigIdIdByTemplateIds(String configId, List<String> templateIds, String templateType) {
		templateRepository.updateConfigIdIsNullByConfigIdAndTemplateType(configId, templateType);
		return templateRepository.updateConfigIdByTemplateIds(configId, templateIds);
	}
}
