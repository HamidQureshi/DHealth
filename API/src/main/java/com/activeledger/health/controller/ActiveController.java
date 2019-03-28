package com.activeledger.health.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.activeledger.health.auth.JwtGenerator;
import com.activeledger.health.model.ActiveResponse;
import com.activeledger.health.model.Resp;
import com.activeledger.health.model.User;
import com.activeledger.health.service.ActiveService;
import com.fasterxml.jackson.databind.ObjectMapper;



@Path("/")
public class ActiveController {

	final static Logger logger = Logger.getLogger(ActiveController.class);
	@Autowired
	ActiveService activeService;
	@Autowired
	JwtGenerator jwtGenerator;
	ObjectMapper mapper;

	public ActiveController() {
		 mapper=new ObjectMapper();
	}
	
	@GET
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
			logger.info("------Registering user-----");
			activeService.save(user);
			String token=jwtGenerator.generate(user);
			resp.setCode(200);
			resp.setDesc("User registered successfully");
			return Response.ok().header("Token", "Token "+token).entity(resp).build();
		}catch(DataIntegrityViolationException e)
		{
			logger.error("------Error in Registering user. Username already exists-----",e);
			resp.setCode(400);
			resp.setDesc("Username already exists");
			return Response.status(Status.BAD_REQUEST).entity(resp).build();
		}catch(Exception e)
		{
			logger.error("------Error in Registering user-----",e);

			resp.setCode(500);
			resp.setDesc("Error registering user");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resp).build();
		}
			
	}
	
	
	@POST
	@Path("/transaction/createProfile")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createProfile(Map<String,Object> transaction,@HeaderParam("Authorization") String token) throws Exception {

		logger.info("------Creating user profile-----");
		Map<String,String> user=activeService.register(token,transaction);
		Resp resp=new Resp();
		if(user.get("error")==null)
		{
			resp.setCode(200);
		resp.setDesc("User Registered Successfully");}
		else
		{
			resp.setCode(400);
			resp.setDesc("User was not able to register");	
		}
		ActiveResponse activeResp=new ActiveResponse();
		activeResp.setResp(resp);
		activeResp.setStream(user);
		return Response.ok().header("Content-Type", "application/json").entity(activeResp).build();
	
	}
	
	@POST
	@Path("/transaction")
	@Consumes("application/json")
	@Produces("application/json")
	public Response sendTransaction(Map<String,Object> transaction,@HeaderParam("Authorization") String token) throws Exception {
		logger.info("------Create and send transaction-----");
		Map<String, String> txn=activeService.sendTransaction(token,transaction);
		Resp resp=new Resp();
		if(txn.get("error")==null)
		{
			resp.setCode(200);
			resp.setDesc("Successfull");}
		else
		{
			resp.setCode(400);
			resp.setDesc("Unsuccessfull");	
		}
		ActiveResponse activeResp=new ActiveResponse();
		activeResp.setResp(resp);
		activeResp.setStream(txn);
		return Response.ok().header("Content-Type", "application/json").entity(activeResp).build();
	}
	
	
	@GET
	@Path("/customLogout")
	@Produces("application/json")
	public Response logout() {
		
		Resp resp=new Resp();
		resp.setCode(200);
		resp.setDesc("Logout was successfull");
		return Response.ok().entity(resp).build();
	}
	
	
	@GET
	@Path("/transaction/users/{userType}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUsers(@PathParam("userType") String type) throws Exception {

		logger.info("------fetching users-----:"+type);
		JSONObject docs=activeService.getUsers(type);
		/*Resp resp=new Resp();
		resp.setCode(200);
		resp.setDesc("User Registered Successfully");
		Map<String,String> temp=new HashMap<>();
		temp.put("test",docs.toString());
		ActiveResponse activeResp=new ActiveResponse();
		activeResp.setResp(resp);
		activeResp.getStream().p;*/
		return Response.ok().header("Content-Type", "application/json").entity(docs.toString()).build();//header("Content-Type", "application/json").entity(activeResp).build();
	
	}

	
}
