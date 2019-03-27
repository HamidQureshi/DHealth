package com.activeledger.health.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class ActiveResponse {

	private Resp resp;
	private Map<String,String> stream;
	public Map<String, String> getStream() {
		return stream;
	}
	public void setStream(Map<String, String> stream) {
		this.stream = stream;
	}
	public Resp getResp() {
		return resp;
	}
	public void setResp(Resp resp) {
		this.resp = resp;
	}

	
	
	
}
