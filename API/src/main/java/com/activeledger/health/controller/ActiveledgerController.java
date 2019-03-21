package com.activeledger.health.controller;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.activeledger.health.config.JwtGenerator;
import com.activeledger.health.model.Resp;
import com.activeledger.health.model.User;
import com.activeledger.health.service.ActiveService;



@Path("/")
public class ActiveledgerController {

	@Autowired
	ActiveService activeService;
	@Autowired
	JwtGenerator jwtGenerator;

	@GET
	@Path("/")
	
	public Response test()
	{
		String desc="I am Batman";

		return Response.ok().entity(desc).build();
	}
	
	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(User user) throws Exception {
		
		Resp resp=new Resp();
		try{
			activeService.save(user);
			String token=jwtGenerator.generate(user);
			resp.setCode(200);
			resp.setDesc("User registered successfully");
			return Response.ok().header("Token", "Token "+token).entity(resp).build();
		}catch(DataIntegrityViolationException e)
		{
			resp.setCode(400);
			resp.setDesc("Username already exists");
			return Response.status(Status.BAD_REQUEST).entity(resp).build();
		}catch(Exception e)
		{
			resp.setCode(500);
			resp.setDesc("Error registering user");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resp).build();
		}
			
	}
		
	@POST
	@Path("/transaction")
	@Consumes("application/json")
	@Produces("application/json")
	public Response sendTransaction(Map<String,Object> transaction,@HeaderParam("Authorization") String token) throws Exception {
		Map<String, String> resp=activeService.sendTransaction(token,transaction);
		return Response.ok().entity(resp).build();
	
	}
	
	

	
	


	
}
