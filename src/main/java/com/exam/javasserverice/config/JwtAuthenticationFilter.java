package com.exam.javasserverice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.javasserverice.service.UserDetailServiceImpl;

import io.jsonwebtoken.JwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private JwtUtills jwtUtills;

	@SuppressWarnings("unused")
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");
		String userName= null;
		String jwtToken = null;
		System.out.println("requestTokenHeader:"+" "+ requestTokenHeader);
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
			jwtToken = requestTokenHeader.substring(7);
			try{
				this.jwtUtills.extractUsername(jwtToken);
			}
			catch(JwtException e) {
				e.printStackTrace();
				System.out.println("Exception Occured");
			}
		}
		else {
			System.out.println("Invalid token,");
		}
		
		//Validate Token
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetail = this.userDetailServiceImpl.loadUserByUsername(userName);
			
			if(this.jwtUtills.validateToken(jwtToken, userDetail)) {	
				UsernamePasswordAuthenticationToken userNamePasswordAuthToken = 
						new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
		
				userNamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthToken);
			}
			else {
				System.out.println("Token is not valid");
			}
		}
		
		filterChain.doFilter(request, response);
		
	}

}
