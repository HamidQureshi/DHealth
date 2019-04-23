package com.activeledger.health.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(Include.NON_NULL)
public class ActiveResponse {

	private Resp resp;

	@JsonProperty("streams")
	private Object streams;

	

	public Resp getResp() {
		return resp;
	}
	public void setResp(Resp resp) {
		this.resp = resp;
	}
	public Object getStreams() {
		return streams;
	}
	public void setStreams(Object streams) {
		this.streams = streams;
	}
	

	
	
	
}
