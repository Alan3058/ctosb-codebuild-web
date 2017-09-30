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

import com.ctosb.codebuild.dao.DatasourceRepository;
import com.ctosb.codebuild.entity.Datasource;

/**
 * 数据源服务
 * @author liliangang-1163
 * @date 2017年9月20日下午1:33:49
 */
@Component
public class DatasourceService {

	@Autowired
	private DatasourceRepository datasourceRepository;

	public Datasource save(Datasource datasource) {
		return datasourceRepository.save(datasource);
	}

	public Datasource getByDataSourceId(String datasourceId) {
		return datasourceRepository.findOne(datasourceId);
	}

	public List<Datasource> getAll() {
		return datasourceRepository.findAll();
	}
}
