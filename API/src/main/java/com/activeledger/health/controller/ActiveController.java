package com.activeledger.health.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.activeledger.health.auth.JwtGenerator;
import com.activeledger.health.model.ActiveResponse;
import com.activeledger.health.model.Resp;
import com.activeledger.health.model.User;
import com.activeledger.health.service.ActiveService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Api(value="Health API")
@RestController

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

	@ApiOperation(value = "Confidential",
			notes = "Please dont run it. You will break the internet."
		
    		//responseContainer = "Response"
    	)
	@RequestMapping(value="/",method=RequestMethod.GET )
	
	public String test() {
		String des="I am Batman (O.O).Shhhhhh!!!!";
		return des;
	}

	
	@ApiOperation(value = "User Registration",
			notes = "Registration of the user",
		
    		responseContainer = "Response"
    	)
	@ApiResponses(value = {  @ApiResponse(code = 200, message = "User registered successfully"),@ApiResponse(code = 400, message = "Username already exists"),
		      @ApiResponse(code = 500, message = "Error Registering User") })
	@PostMapping(value="/register",produces="application/json",consumes="application/json")

	public ResponseEntity<Resp> register( @ApiParam(value = "User Details", required = true) @RequestBody User user) throws Exception {

		Resp resp = new Resp();
		try {
			logger.info("------Registering user-----");
			activeService.register(user);
			String token = jwtGenerator.generate(user);
			resp.setCode(200);
			resp.setDesc("User registered successfully");
			HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("Token", "Token " + token);
		    return ResponseEntity.ok()
		    	      .headers(responseHeaders)
		    	      .body(resp);
		} catch (DataIntegrityViolationException e) {
			logger.error("------Error in Registering user. Username already exists-----", e);
			resp.setCode(400);
			resp.setDesc("Username already exists");
			return ResponseEntity.badRequest().body(resp);
		} catch (Exception e) {
			logger.error("------Error in Registering user-----", e);
			resp.setCode(500);
			resp.setDesc("Error registering user");
		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
		}

	}

	
	@ApiOperation(value = "Create User Profile",
			notes = "Detailed user profile",
		
    		responseContainer = "Response"
    	)
	@ApiResponses(value = {  @ApiResponse(code = 200, message = "User profile created successfully"),@ApiResponse(code = 400, message = "User profile not created")})

	@PostMapping(value="/transaction/createProfile",produces="application/json",consumes="application/json")

	public ResponseEntity<ActiveResponse> createProfile(@ApiParam(format="",value = "Transaction to send to activeledger", required = true) @RequestBody Map<String, Object> transaction,@ApiParam(value = "Token for authentication of user", required = true) @RequestHeader("Authorization") String token)
			throws Exception {

		logger.info("------Creating user profile-----");
		JSONObject user = activeService.createProfile(token, transaction);
		ActiveResponse activeResp = new ActiveResponse();

		Resp resp = new Resp();
		HttpStatus st;
		if (!user.has("error")) {
			resp.setCode(200);
			resp.setDesc("User profile created successfully");
			st = HttpStatus.OK;
			if (user.has("stream")) {
				activeResp.setStreams(user.getJSONObject("stream").toMap());

			} else
				activeResp.setStreams(user.getJSONArray("streams").toList());

		} else {

			resp.setCode(400);
			resp.setDesc("User profile not created:"+user.get("error"));
			st = HttpStatus.BAD_REQUEST;
		}
		activeResp.setResp(resp);
		


		return ResponseEntity.status(st).header("Content-Type", "application/json").body(activeResp);

	}
	
	@ApiOperation(value = "Send Transaction",
			notes = "Any Transaction of correct format can be send using this endpoint",
		
    		responseContainer = "Response"
    	)
	@ApiResponses(value = {  @ApiResponse(code = 200, message = "Successfull"), @ApiResponse(code = 400, message = "Unsuccessfull")
		      })

	@PostMapping(value="/transaction",produces="application/json",consumes="application/json")
	public ResponseEntity<ActiveResponse> sendTransaction(@RequestBody Map<String, Object> transaction,@ApiParam(value = "Token for authentication of user", required = true) @RequestHeader("Authorization") String token)
			throws Exception {
		logger.info("------Create and send transaction-----");
		Map<String, Object> txn = activeService.sendTransaction(token, transaction);
		Resp resp = new Resp();
		ActiveResponse activeResp = new ActiveResponse();
		HttpStatus st;
		if (txn.get("error") == null) {
			resp.setCode(200);
			resp.setDesc("Successfull");
			st = HttpStatus.OK;

		} else {
			resp.setCode(400);
			resp.setDesc("Unsuccessfull");
			st = HttpStatus.BAD_REQUEST;
		}

		activeResp.setResp(resp);
		activeResp.setStreams(txn);
		return ResponseEntity.status(st).header("Content-Type", "application/json").body(activeResp);

	}
	
	
	@ApiOperation(value = "Get Users",
			notes = "Gets patients or doctors based on the userType path parameter i.e. patients or doctors",
		
    		responseContainer = "Response"
    	)
	@ApiResponses(value = {  @ApiResponse(code = 200, message = "Users")
		      })

	@GetMapping(value="/transaction/users/{userType}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<ActiveResponse> getUsers(@PathVariable ("userType") String type) throws Exception {

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

		return ResponseEntity.ok().header("Content-Type", "application/json").body(activeResp);// header("Content-Type",
																									// "application/json").entity(activeResp).build();

	}

	
	
	@ApiOperation(value = "Get Assigned Patients",
			notes = "Get patients assigned to the doctor. Doctor is extracted from the token",
		
    		responseContainer = "Response"
    	)
	@ApiResponses(value = {  @ApiResponse(code = 200, message = "patients")
		      })
	
	@GetMapping(value="/transaction/patients",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<ActiveResponse> getAssignedPatients(@ApiParam(value = "Token for authentication of user", required = true)@RequestHeader("Authorization") String token) throws Exception {

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

		return ResponseEntity.ok().body(activeResp);
		// return Response.ok().header("Content-Type",
		// "application/json").entity(docs.toString()).build();//header("Content-Type",
		// "application/json").entity(activeResp).build();

	}
	
	@ApiOperation(value = "Get User",
			notes = "Get User with the given identity",
		
    		responseContainer = "Response"
    	)
	@ApiResponses(value = {  @ApiResponse(code = 200, message = "User")
		      })

	@GetMapping(value="/transaction/user/{identity}",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<String> getUser(@PathVariable("identity") String identity) throws Exception {

		// "bbf54e123d47f2ef146590143cf0e8481a7dde54612b8cdc45b7bc568513f50d"
		JSONObject docs = activeService.retrieveUser(identity);
		return ResponseEntity.ok().body(docs.toString());
	}

	
	@ApiOperation(value = "Get User Reports",
			notes = "Get User Reports. User is extracted using the token",
		
    		responseContainer = "Response"
    	)
	@ApiResponses(value = {  @ApiResponse(code = 200, message = "Reports")
		      })

	@GetMapping(value="/transaction/reports",produces=MediaType.APPLICATION_JSON)
	public ResponseEntity<ActiveResponse> getReports(@ApiParam(value = "Token for authentication of user", required = true)@RequestHeader("Authorization") String token) throws Exception {

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

		return ResponseEntity.ok().body(activeResp);

	}

}
