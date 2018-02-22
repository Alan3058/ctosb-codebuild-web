
package com.ctosb.codebuild.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Index接口")
public class IndexController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/init/data")
	@ApiOperation(value = "重置初始化数据")
	public boolean initDatabase() throws SQLException {
		String schemaSql = "/sql/schema.sql";
		String dataSql = "/sql/data.sql";
		jdbcTemplate.execute("RUNSCRIPT FROM  'classpath:" + schemaSql + "'");
		jdbcTemplate.execute("RUNSCRIPT FROM  'classpath:" + dataSql + "'");
		return true;
	}
}
