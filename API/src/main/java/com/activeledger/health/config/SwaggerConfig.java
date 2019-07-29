package com.activeledger.health.config;


import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {
	
	 @Bean
	    public Docket produceApi(){
	    return new Docket(DocumentationType.SWAGGER_2)
	    .apiInfo(apiInfo())
	    .select()
	    .apis(RequestHandlerSelectors.basePackage("com.activeledger.health"))
	    .paths(PathSelectors.any())
	    .build();
	}
	// Describe your apis
	private ApiInfo apiInfo() {
		Contact contact = new Contact(
		         "Nauman",
		         "http://www.activeledger.io", 
		         "nauman@ativeledger.io"
		 );
	    return new ApiInfoBuilder()
	    .title("HealthApp Rest APIs")
	    .description("This page lists all the rest apis for Health App.")
	    .version("1.0-SNAPSHOT")
	    .contact(contact)
	    .build();
	}

	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry)
	    {
	        //enabling swagger-ui part for visual documentation
	        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	    }
}
