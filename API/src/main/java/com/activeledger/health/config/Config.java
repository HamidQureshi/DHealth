package com.activeledger.health.config;


import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;

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
    public ServletRegistrationBean<ServletContainer> serviceServlet() {
	
        ResourceConfig resourceConfig=new ResourceConfig();
       // resourceConfig.packages("com.activeledger.health");
        resourceConfig.register(CORSFilter.class);
        resourceConfig.register(ActiveController.class);
        resourceConfig.register(ApiListingResource.class);
        resourceConfig.register(SwaggerSerializers.class);
        
      
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        String basePath = "/api/";
            if (!basePath.endsWith("/") || !basePath.startsWith("/"))
            throw new IllegalArgumentException("Overriden getServiceBasePath() must start and end with a '/'");
        ServletRegistrationBean<ServletContainer> servletRegistrationBean = new ServletRegistrationBean<ServletContainer>(servletContainer, basePath + "*");
        servletRegistrationBean.setName("serviceServlet");
        configureSwagger();
        return servletRegistrationBean;
    }

	
	

    private void configureSwagger() {
        // Available at localhost:port/api/swagger.json
  

        BeanConfig config = new BeanConfig();
       /* this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);*/
        config.setConfigId("Health-app");
        config.setTitle("Health API");
        config.setVersion("v1");
        config.setContact("Nomi");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath("/api");
        config.setResourcePackage("com.activeledger.health");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}
