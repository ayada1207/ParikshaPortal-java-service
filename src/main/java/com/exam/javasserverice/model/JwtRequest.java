package com.exam.javasserverice.model;

public class JwtRequest {

	String userName;
	String password;
	
	public JwtRequest() {};
	
	public JwtRequest(String userName, String password) {
		
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
