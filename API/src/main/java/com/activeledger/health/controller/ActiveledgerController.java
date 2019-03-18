package com.activeledger.health.controller;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.activeledger.health.model.NamespaceRequest;
import com.activeledger.health.model.RegisterRequest;
import com.activeledger.health.model.RegisterResponse;
import com.activeledger.health.service.ActiveService;



@Path("/rest/")
public class ActiveledgerController {

	@Autowired
	ActiveService activeService;

	
	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(RegisterRequest register) throws Exception {
		
		Map<String, String> resp=activeService.register(register);
		if(resp.get("id")!=null)
			return Response.ok().build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
	}
	
	
	@POST
	@Path("/namespace")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createNamespace(String namespace) throws Exception {
		return null;
		/*Map<String, String> resp=activeService.createNamespace(namespace);
		if(resp.get("id")!=null)
			return Response.ok().build();
		return Response.status(Status.NOT_ACCEPTABLE).build();
*/	}
	
	
	
	@POST
	@Path("/transaction")
	@Consumes("application/json")
	@Produces("application/json")
	public Response sendTransaction(Map<String,Object> transaction) throws Exception {
		
		Map<String, String> resp=activeService.sendTransaction(transaction);
			return Response.ok().entity(resp).build();
	}
	
	

	
	


	
}
