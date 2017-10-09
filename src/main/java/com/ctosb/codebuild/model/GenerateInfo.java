/*
 * @Project Name: ctosb-codebuild-web
 * @File Name: GenerateInfo.java
 * @Package Name: com.ctosb.codebuild.model
 * @Date: 2017年9月29日上午11:36:58
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.ctosb.codebuild.model;

/**
 * 生成文件信息
 * @author liliangang-1163
 * @date 2017年9月29日上午11:36:58
 * @see
 */
public class GenerateInfo {

	/**
	 * 生成源文件
	 */
	private String sourceFolder;
	/**
	 * 生成包路径
	 */
	private String packagePath;
	/**
	 * 生成包名
	 */
	private String packageName;
	/**
	 * 生成内容
	 */
	private String content;
	/**
	 * 生成文件名称
	 */
	private String fileName;

	public String getSourceFolder() {
		return sourceFolder;
	}

	public void setSourceFolder(String sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	public String getPackagePath() {
		return packagePath;
	}

	public void setPackagePath(String packagePath) {
		this.packagePath = packagePath;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
