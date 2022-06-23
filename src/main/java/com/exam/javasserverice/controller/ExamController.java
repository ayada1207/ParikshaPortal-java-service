package com.exam.javasserverice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.javasserverice.model.Users;
import com.exam.javasserverice.repository.UserRepository;
import com.exam.javasserverice.service.UsersService;

@RestController()
@RequestMapping("/examController")
@CrossOrigin("*")
public class ExamController {

	@Autowired
	private UserRepository userRepo;
	@Autowired(required=true)
	
	private UsersService usersService;
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public Users saveUsers(@RequestBody Users users) {
		return this.usersService.creatUser(users);	
	}
	
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers() {
		return ResponseEntity.ok(this.userRepo.findAll());	
	}
	
	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	public Users getUser(@PathVariable("userName") String userName) {
		return this.usersService.findUserByUserName(userName);
	}
	
	@RequestMapping(value = "/{userName}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("userName") String userName) {
		
	}
}
