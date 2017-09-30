
package com.ctosb.codebuild.entity;

import java.util.List;

/**
 * 配置详情信息
 * @author Alan
 * @date 2017-9-24 下午1:15:32
 */
public class ConfigItem extends Config {

	private Datasource datasource;
	private List<Template> templates;

	public Datasource getDatasource() {
		return datasource;
	}

	public void setDatasource(Datasource datasource) {
		this.datasource = datasource;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}
}
