/*package com.activeledger.health.security.config;

import javax.ws.rs.ApplicationPath;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.config.SwaggerConfigLocator;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.jaxrs.listing.ApiListingResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;



@Component
@ApplicationPath("/api")
public class SwaggerConfig extends ResourceConfig {
	public SwaggerConfig()
	{
		
		    BeanConfig swaggerConfig = new BeanConfig();
		    swaggerConfig.setBasePath("/api");
		              SwaggerConfigLocator.getInstance().putConfig(SwaggerContextService.CONFIG_ID_DEFAULT, swaggerConfig);

		     packages("com.activeledger.health",
		                  ApiListingResource.class.getPackage().getName());
		  
	}
}
*/