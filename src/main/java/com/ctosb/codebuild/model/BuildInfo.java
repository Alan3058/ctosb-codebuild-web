
package com.ctosb.codebuild.model;

import java.util.Collection;

/**
 * 每张表的每个模版的构建信息，用于提供编译对应模版的数据源
 * @author liliangang-1163
 * @date 2017年10月9日下午1:24:20
 */
public class BuildInfo {

	private TableInfo tableInfo;
	private Collection<ColumnInfo> columnInfos;
	private String basePackageName;
	private String packageName;

	public TableInfo getTableInfo() {
		return tableInfo;
	}

	public Collection<ColumnInfo> getColumnInfos() {
		return columnInfos;
	}

	public String getBasePackageName() {
		return basePackageName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setTableInfo(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
	}

	public void setColumnInfos(Collection<ColumnInfo> columnInfos) {
		this.columnInfos = columnInfos;
	}

	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
