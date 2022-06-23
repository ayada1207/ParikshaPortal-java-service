package com.exam.javasserverice.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.javasserverice.config.JwtUtills;
import com.exam.javasserverice.model.JwtRequest;
import com.exam.javasserverice.model.JwtResponse;
import com.exam.javasserverice.service.UserDetailServiceImpl;

	@RestController
	public class AuthenticationController {
		
		@Autowired
		public AuthenticationManager authManager;
		@Autowired
		public UserDetailServiceImpl userDetailServiceImpl;
		@Autowired
		private JwtUtills jwtUtills;
		
		//generateToken
		
		@PostMapping("/generate-token")
		public ResponseEntity<?> generateToke(@RequestBody JwtRequest jwtRequest) throws Exception{
			try {
				authenticate(jwtRequest.getUserName(),jwtRequest.getPassword());
			}
			catch(UsernameNotFoundException e) {
				e.printStackTrace();
				throw new Exception("User Not Found ");
			}
			
			UserDetails userDetail = this.userDetailServiceImpl.loadUserByUsername(jwtRequest.getUserName());
			String token = this.jwtUtills.generateToken(userDetail);
			return ResponseEntity.ok(new JwtResponse(token));
			
		}
		
		private void authenticate(String userName, String password) {
			try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		}
			catch(DisabledException e)
			{
				e.printStackTrace();
			}
			
		}

  }
