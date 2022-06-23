package com.exam.javasserverice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.exam.javasserverice.model.Roles;
import com.exam.javasserverice.model.Users;

public interface UserRoleRepository extends MongoRepository<Roles, Long>{
	
	public Roles findByRole(String role);


}
