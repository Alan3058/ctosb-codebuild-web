/*
 * @Project Name: springboot-demo
 * @File Name: UserMapper.java
 * @Package Name: com.ctosb.springboot.mapper.UserMapper
 * @Date: 2017年9月1日下午12:57:29
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package com.ctosb.codebuild.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ctosb.codebuild.entity.Template;

/**
 * 模版dao
 * @author liliangang-1163
 * @date 2017年9月20日下午1:33:29
 */
public interface TemplateRepository extends JpaRepository<Template, String> {

	List<Template> findByConfigId(String configId);

	List<Template> findByConfigIdIn(List<String> configIds);
}
