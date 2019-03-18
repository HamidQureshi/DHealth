package com.activeledger.health.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.activeledger.health.config.Config;

@SpringBootApplication
public class ActiveHealthApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ActiveHealthApplication.class,Config.class).run(args);
	}

}
