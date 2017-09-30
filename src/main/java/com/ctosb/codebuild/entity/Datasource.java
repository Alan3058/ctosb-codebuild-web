
package com.ctosb.codebuild.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 数据源信息
 * @author Alan
 * @date 2017-9-21 下午1:28:46
 */
@Entity
public class Datasource {

	/**
	 * 配置id标识
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String datasourceId;
	/**
	 * 名称
	 */
	@Column
	private String datasourceName;
	/**
	 * jdbc驱动
	 */
	@Column
	private String jdbcDriver;
	/**
	 * jdbcUrl
	 */
	@Column
	private String jdbcUrl;
	/**
	 * 数据库用户名
	 */
	@Column
	private String jdbcUserName;
	/**
	 * 数据库密码
	 */
	@Column
	private String jdbcPassword;

	public String getDatasourceId() {
		return datasourceId;
	}

	public void setDatasourceId(String datasourceId) {
		this.datasourceId = datasourceId;
	}

	public String getDatasourceName() {
		return datasourceName;
	}

	public void setDatasourceName(String datasourceName) {
		this.datasourceName = datasourceName;
	}

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUserName() {
		return jdbcUserName;
	}

	public void setJdbcUserName(String jdbcUserName) {
		this.jdbcUserName = jdbcUserName;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}
}
