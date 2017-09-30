
package com.ctosb.codebuild.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 配置信息
 * @author Alan
 * @date 2017-9-21 下午1:29:16
 */
@Entity
public class Config {

	/**
	 * 配置id标识
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String configId;
	/**
	 * 数据源id
	 */
	@Column
	private String datasourceId;
	/**
	 * 配置名称
	 */
	@Column
	private String configName;

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}
}
