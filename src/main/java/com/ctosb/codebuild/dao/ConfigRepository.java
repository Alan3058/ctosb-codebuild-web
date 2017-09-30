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
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ctosb.codebuild.entity.Config;

/**
 * 配置dao
 * @author liliangang-1163
 * @date 2017年9月20日下午1:33:14
 */
public interface ConfigRepository extends JpaRepository<Config, String> {

	List<Config> getByConfigNameLike(String userName);

	@Modifying
	@Query("update Config a set a.datasourceId = ?1 where a.configId = ?2")
	int updateDatasourceIdByConfigId(String datasourceId, String configId);
}
