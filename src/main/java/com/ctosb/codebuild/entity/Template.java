
package com.ctosb.codebuild.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 模版信息
 * @author Alan
 * @date 2017-9-21 下午1:29:15
 */
@Entity
public class Template {

	/**
	 * 模版id
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String templateId;
	/**
	 * 配置id标识
	 */
	@Column
	private String configId;
	/**
	 * 模版名称
	 */
	@Column
	private String templateName;
	/**
	 * 模版内容
	 */
	@Column(length = 20000)
	private String templateContent;
	/**
	 * 模版类型（bySql、byTable）
	 */
	@Column
	private String templateType;
	/**
	 * 生成源文件夹
	 */
	@Column
	private String generateSourceFolder;
	/**
	 * 生成文件包路径
	 */
	@Column
	private String generatePackagePath;
	/**
	 * 生成文件后缀
	 */
	@Column
	private String generateSuffix;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	
	public String getTemplateType() {
		return templateType;
	}

	
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getGenerateSourceFolder() {
		return generateSourceFolder;
	}

	public void setGenerateSourceFolder(String generateSourceFolder) {
		this.generateSourceFolder = generateSourceFolder;
	}

	public String getGeneratePackagePath() {
		return generatePackagePath;
	}

	public void setGeneratePackagePath(String generatePackagePath) {
		this.generatePackagePath = generatePackagePath;
	}

	public String getGenerateSuffix() {
		return generateSuffix;
	}

	public void setGenerateSuffix(String generateSuffix) {
		this.generateSuffix = generateSuffix;
	}
}
