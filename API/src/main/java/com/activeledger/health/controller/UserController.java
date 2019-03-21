/*package com.activeledger.health.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.activeledger.health.model.User;
import com.activeledger.health.service.UserService;

@Path("/")
@Consumes("application/json")
public class UserController {

	@Autowired
	UserService userService;

	@GET
	@Path("/")
	public Response test() {
		
		System.out.println("Testing");
	
		return Response.ok().build();
	}
	@POST
	@Path("/registration")
	public Response createNewUser(User user) {
		
		System.out.println("User Registration:"+user);
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			throw new RuntimeException("User already exists");
		}
		
			userService.save(user);

		return Response.ok().build();
	}


	


}
*/