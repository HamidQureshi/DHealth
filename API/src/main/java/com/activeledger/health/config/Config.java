package com.activeledger.health.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;

import java.util.HashSet;
import java.util.Set;

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

public class Config  {
	
	
	@Bean
    public ServletRegistrationBean serviceServlet() {
	
        ResourceConfig resourceConfig=new ResourceConfig();
       // resourceConfig.packages("com.activeledger.health");
        resourceConfig.register(CORSFilter.class);
        resourceConfig.register(ActiveController.class);
        configureSwagger();
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        String basePath = "/";
            if (!basePath.endsWith("/") || !basePath.startsWith("/"))
            throw new IllegalArgumentException("Overriden getServiceBasePath() must start and end with a '/'");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servletContainer, basePath + "*");
        servletRegistrationBean.setName("serviceServlet");
        
        return servletRegistrationBean;
    }
	
	
	
	  private void configureSwagger() {
	        BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("1.0");
	        beanConfig.setSchemes(new String[]{"http"});
	        beanConfig.setTitle("API");
	        beanConfig.setHost("");
	        beanConfig.setBasePath("/");
	        beanConfig.setResourcePackage("com.activeledger.health");
	        beanConfig.setPrettyPrint(true);
	        beanConfig.setScan(true);
	    }
	
}
