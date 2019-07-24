/*import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

package com.activeledger.health.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
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
@Configuration
@PropertySource("classpath:health.properties")
@ComponentScan("basePackages = com.activeledger.health")
@EnableTransactionManagement
@EntityScan({
    "com.activeledger.health"})
@EnableJpaRepositories({
"com.activeledger.health"})

public class Config extends ResourceConfig {
	
	
	@Bean
    public ServletRegistrationBean serviceServlet() {
	
        ResourceConfig resourceConfig=new ResourceConfig();
       // resourceConfig.packages("com.activeledger.health");
        resourceConfig.register(CORSFilter.class);
        resourceConfig.register(ApiListingResource.class);
       // this.register(ApiListingResource.class);
        resourceConfig.register(SwaggerSerializers.class);
        resourceConfig.register(ActiveController.class);
       // configureSwagger();
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        String basePath = "/api/";
            if (!basePath.endsWith("/") || !basePath.startsWith("/"))
            throw new IllegalArgumentException("Overriden getServiceBasePath() must start and end with a '/'");
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servletContainer, basePath + "*");
        servletRegistrationBean.setName("serviceServlet");
        configureSwagger();
        property(ServletProperties.FILTER_FORWARD_ON_404, true);
        return servletRegistrationBean;
    }
	
	 private void configureSwagger() {
	        BeanConfig beanConfig = new BeanConfig();
	        beanConfig.setVersion("1.0");
	        beanConfig.setSchemes(new String[]{"http"});
	        beanConfig.setTitle("HealthWeb API");
	        //beanConfig.setHost("localhost:5555/swagger-ui");
	        beanConfig.setBasePath("/api");
	        beanConfig.setResourcePackage("com.activeledger.health.controller");
	        beanConfig.setPrettyPrint(true);
	        beanConfig.setScan(true);
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


package com.activeledger.health.config;


import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
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
   
    public class Config1 extends ResourceConfig {

        @Value("${spring.jersey.application-path:/}")
        private String apiPath;

        
        
       
        public Config1() {
            // Register endpoints, providers, ...
            this.registerEndpoints();
        }

        @PostConstruct
        public void init() {
            // Register components where DI is needed
            this.configureSwagger();
            //this.registerEndpoints();
        }

        private void registerEndpoints() {
            //this.register(GroupResource.class);
            this.register(CORSFilter.class);
           // this.register(ApiListingResource.class);
           // this.register(ApiListingResource.class);
           // this.register(SwaggerSerializers.class);
            this.register(ActiveController.class);
            // Access through /<Jersey's servlet path>/application.wadl
          //  this.register(WadlResource.class);
        }

        private void configureSwagger() {
            // Available at localhost:port/api/swagger.json
            this.register(ApiListingResource.class);
            this.register(SwaggerSerializers.class);

            BeanConfig config = new BeanConfig();
            config.setConfigId("Health-app");
            config.setTitle("Health API");
            config.setVersion("v1");
            config.setContact("Nomi");
            config.setSchemes(new String[] { "http", "https" });
            config.setBasePath(this.apiPath);
            config.setResourcePackage("com.activeledger.health");
            config.setPrettyPrint(true);
            config.setScan(true);
        }
}
*/