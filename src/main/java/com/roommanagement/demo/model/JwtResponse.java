package com.roommanagement.demo.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;

	public String getUsername() {
		return username;
	}

	public JwtResponse(String jwttoken, String username) {
		this.jwttoken = jwttoken;
		this.username = username;
	}

	public String getToken() {
		return this.jwttoken;
	}
}