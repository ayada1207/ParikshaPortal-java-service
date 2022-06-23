package com.exam.javasserverice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.javasserverice.service.UserDetailServiceImpl;

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	@Configuration
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
		@Autowired
		private UserDetailServiceImpl userDetailssServiveImpl;
		@Autowired
		private JwtAuthenticationEntryPoint unauthorizedException;
		@Autowired
		private JwtAuthenticationFilter jwtAuthFilter;
		
		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			// TODO Auto-generated method stub
			return super.authenticationManagerBean();
		}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return NoOpPasswordEncoder.getInstance();
		}
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http 
					.csrf()
					.disable()
					.cors()
					.disable()
					.authorizeRequests()
					.antMatchers("/generate-token","/user/").permitAll()
					.antMatchers(HttpMethod.OPTIONS).permitAll()
					.anyRequest().authenticated()
					.and().exceptionHandling().authenticationEntryPoint(unauthorizedException)
					.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
			http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
			
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(this.userDetailssServiveImpl).passwordEncoder(passwordEncoder());
		}

}
