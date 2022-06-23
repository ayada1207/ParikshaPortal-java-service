package com.exam.javasserverice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.NonUniqueSessionRepositoryException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.javasserverice.model.Users;
import com.exam.javasserverice.repository.UserRepository;

	@Service
	public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = this.userRepo.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid Credentials!");
		}
		return user;
		
	}

}
