package com.exam.javasserverice.model;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

	private String authority;
	
	
	public Authority(String authority) {
		
		authority = authority;
	}
	
	public Authority() {};


	public void setAuthority(String authority) {
		authority = authority;
	}


	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
	

}
