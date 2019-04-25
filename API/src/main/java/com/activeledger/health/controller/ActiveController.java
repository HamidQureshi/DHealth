package com.activeledger.health.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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


@Api
@Path("/")
public class ActiveController {

	final static Logger logger = Logger.getLogger(ActiveController.class);
	@Autowired
	ActiveService activeService;
	@Autowired
	JwtGenerator jwtGenerator;
	ObjectMapper mapper;

	public ActiveController() {
		mapper = new ObjectMapper();
	}

	@ApiOperation(value = "Here lies my secrets",
			notes = "This is the most confidential operation. Dont use it if you are not ready.",
			response = String.class,
    		responseContainer = "Response"
    	)
	@GET
	public Response test() {
		String desc = "I am Batman";
		return Response.ok().entity(desc).build();
	}

	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(User user) throws Exception {

		Resp resp = new Resp();
		try {
			logger.info("------Registering user-----");
			activeService.save(user);
			String token = jwtGenerator.generate(user);
			resp.setCode(200);
			resp.setDesc("User registered successfully");
			return Response.ok().header("Token", "Token " + token).entity(resp).build();
		} catch (DataIntegrityViolationException e) {
			logger.error("------Error in Registering user. Username already exists-----", e);
			resp.setCode(400);
			resp.setDesc("Username already exists");
			return Response.status(Status.BAD_REQUEST).entity(resp).build();
		} catch (Exception e) {
			logger.error("------Error in Registering user-----", e);
			resp.setCode(500);
			resp.setDesc("Error registering user");
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(resp).build();
		}

	}

	@POST
	@Path("/transaction/createProfile")
	@Consumes("application/json")
	@Produces("application/json")
	public Response createProfile(Map<String, Object> transaction, @HeaderParam("Authorization") String token)
			throws Exception {

		logger.info("------Creating user profile-----");
		JSONObject user = activeService.register(token, transaction);
		ActiveResponse activeResp = new ActiveResponse();

		Resp resp = new Resp();
		Status st;
		if (!user.has("error")) {
			resp.setCode(200);
			resp.setDesc("User Registered Successfully");
			st = Status.OK;

		} else {
			resp.setCode(400);
			resp.setDesc("User was not able to register");
			st = Status.BAD_REQUEST;
		}
		activeResp.setResp(resp);
		if (user.has("stream")) {
			activeResp.setStreams(user.getJSONObject("stream").toMap());

		} else
			activeResp.setStreams(user.getJSONArray("streams").toList());

		return Response.status(st).header("Content-Type", "application/json").entity(activeResp).build();

	}

	@POST
	@Path("/transaction")
	@Consumes("application/json")
	@Produces("application/json")
	public Response sendTransaction(Map<String, Object> transaction, @HeaderParam("Authorization") String token)
			throws Exception {
		logger.info("------Create and send transaction-----");
		Map<String, Object> txn = activeService.sendTransaction(token, transaction);
		Resp resp = new Resp();
		ActiveResponse activeResp = new ActiveResponse();
		Status st;
		if (txn.get("error") == null) {
			resp.setCode(200);
			resp.setDesc("Successfull");
			st = Status.OK;

		} else {
			resp.setCode(400);
			resp.setDesc("Unsuccessfull");
			st = Status.BAD_REQUEST;
		}

		activeResp.setResp(resp);
		activeResp.setStreams(txn);
		return Response.status(st).header("Content-Type", "application/json").entity(activeResp).build();

	}

	@GET
	@Path("/customLogout")
	@Produces("application/json")
	public Response logout() {

		Resp resp = new Resp();
		resp.setCode(200);
		resp.setDesc("Logout was successfull");
		return Response.ok().entity(resp).build();
	}

	@GET
	@Path("/transaction/users/{userType}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUsers(@PathParam("userType") String type) throws Exception {

		logger.info("------fetching users-----:" + type);
		ActiveResponse activeResp = new ActiveResponse();
		JSONObject user = activeService.getUsers(type);
		Resp resp = new Resp();
		resp.setCode(200);
		resp.setDesc("Successfull");
		activeResp.setResp(resp);
		if (user.has("stream")) {
			activeResp.setStreams(user.getJSONObject("stream").toMap());

		} else
			activeResp.setStreams(user.getJSONArray("streams").toList());

		return Response.ok().header("Content-Type", "application/json").entity(activeResp).build();// header("Content-Type",
																									// "application/json").entity(activeResp).build();

	}

	@GET
	@Path("/transaction/patients")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getAssignedPatients(@HeaderParam("Authorization") String token) throws Exception {

		JSONObject docs = activeService.getAssignedPatients(token);
		ActiveResponse activeResp = new ActiveResponse();
		Resp resp = new Resp();
		resp.setCode(200);
		resp.setDesc("Successfull");
		activeResp.setResp(resp);
		if (docs.has("streams")) {

			activeResp.setStreams(docs.getJSONArray("streams").toList());
		} else
			activeResp.setStreams(docs.getJSONObject("stream").toMap());

		return Response.ok().entity(activeResp).build();
		// return Response.ok().header("Content-Type",
		// "application/json").entity(docs.toString()).build();//header("Content-Type",
		// "application/json").entity(activeResp).build();

	}

	@GET
	@Path("/user/{identity}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUser(@PathParam("identity") String identity) throws Exception {

		// "bbf54e123d47f2ef146590143cf0e8481a7dde54612b8cdc45b7bc568513f50d"
		JSONObject docs = activeService.retrieveUser(identity);
		return Response.ok().entity(docs.toString()).build();
	}

	@GET
	@Path("/transaction/reports")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getReports(@HeaderParam("Authorization") String token) throws Exception {

		JSONObject reports = activeService.getReports(token);
		ActiveResponse activeResp = new ActiveResponse();
		Resp resp = new Resp();
		resp.setCode(200);
		resp.setDesc("Successfull");
		activeResp.setResp(resp);
		if (reports.has("streams"))
			activeResp.setStreams(reports.getJSONArray("streams").toList());
		else
			activeResp.setStreams(reports.getJSONObject("stream").toMap());

		return Response.ok().entity(activeResp).build();

	}

}
