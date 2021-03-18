package com.chao.wiki.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

/*
使用MapperScan扫描所有Mapper接口
 */

@ComponentScan("com.chao")
@SpringBootApplication
@MapperScan("com.chao.wiki.mapper")
public class WikiApplication {

	private static final Logger logger = LoggerFactory.getLogger(WikiApplication.class);

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(WikiApplication.class);
		ConfigurableEnvironment env = app.run(args).getEnvironment();

		logger.info("start successfully!");
		logger.info("address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));

//		SpringApplication.run(WikiApplication.class, args);
	}

}
