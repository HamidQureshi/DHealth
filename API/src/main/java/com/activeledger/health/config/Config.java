package com.activeledger.health.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.activeledger.health.controller.ActiveController;

@Configuration
@PropertySource("classpath:health.properties")
@ComponentScan("basePackages = com.activeledger.health")
@EnableTransactionManagement
@EntityScan({
    "com.activeledger.health"})
@EnableJpaRepositories({
"com.activeledger.health"})
public class Config {
	
	@Bean
    public ServletRegistrationBean serviceServlet() {
	
        ResourceConfig resourceConfig=new ResourceConfig();
       // resourceConfig.packages("com.activeledger.health");
        resourceConfig.register(CORSFilter.class);
        resourceConfig.register(ActiveController.class);
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        String basePath = "/";
             if (!basePath.endsWith("/") || !basePath.startsWith("/"))
            throw new IllegalArgumentException("Overriden getServiceBasePath() must start and end with a '/'");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servletContainer, basePath + "*");
        servletRegistrationBean.setName("serviceServlet");
        
        return servletRegistrationBean;
    }
	
}
