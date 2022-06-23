package com.exam.javasserverice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.javasserverice.model.Roles;
import com.exam.javasserverice.model.Users;
import com.exam.javasserverice.repository.UserRepository;
import com.exam.javasserverice.repository.UserRoleRepository;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository roleRepository;
	

	@Override
	public Users creatUser(Users user) throws UsernameNotFoundException {
		Users local = userRepository.findByUserName(user.getUserName());
		if(local != null) {
			System.out.println("User already exist");
		}
		else {
			local = this.userRepository.save(user);
		}
		return local;
	}

	@Override
	public Users findUserByUserName(String userName) {
		
		return userRepository.findByUserName(userName);
	}


	@Override
	public void deleteUserByUserName(String userName) {
		this.userRepository.deleteById(null);
		
	}
	
	//Create a method for save a new user, encrypt the password and set a role for the user.
	
	

}
