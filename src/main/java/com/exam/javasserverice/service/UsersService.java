package com.exam.javasserverice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.exam.javasserverice.model.Users;

public interface UsersService {
	
	//creating User
	
	public Users creatUser(Users user);
	
	//finding user by userId
	
	public Users findUserByUserName(String userName);

	//delete by UserName
	
	public void deleteUserByUserName(String userName);

}
