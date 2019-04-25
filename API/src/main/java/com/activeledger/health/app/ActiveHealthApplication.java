package com.activeledger.health.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.activeledger.health.config.Config;

@SpringBootApplication(exclude = MongoAutoConfiguration.class)

public class ActiveHealthApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ActiveHealthApplication.class,Config.class).run(args);
	}
}
