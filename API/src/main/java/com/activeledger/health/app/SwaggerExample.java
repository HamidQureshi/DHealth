package com.activeledger.health.app;

import io.swagger.annotations.ApiModel;


@ApiModel
public class SwaggerExample {

	private String name;
	private String lastname;
	private String ge;
	private String gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getGe() {
		return ge;
	}
	public void setGe(String ge) {
		this.ge = ge;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
