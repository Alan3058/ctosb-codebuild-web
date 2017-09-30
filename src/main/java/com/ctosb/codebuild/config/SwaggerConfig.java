/*
 * @Project Name: springboot-demo
 * @File Name: SwaggerConfig.java
 * @Package Name: com.ctosb.springboot.swagger
 * @Date: 2017年9月1日下午6:22:18
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.ctosb.codebuild.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TODO
 * @author liliangang-1163
 * @date 2017年9月1日下午8:22:18
 * @see
 */
@Configuration
@EnableSwagger2 // 启用 Swagger
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("cto codebuild web接口列表")//大标题
            .version("1.0")//版本
            .build();
    }
}