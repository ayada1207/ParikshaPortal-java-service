package com.exam.javasserverice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;


import com.exam.javasserverice.model.Users;

public interface UserRepository extends MongoRepository<Users, Long>{
	
		public Users findByUserName(String userName);
		public Users existsByEmail(String email);

		

	
	

}
