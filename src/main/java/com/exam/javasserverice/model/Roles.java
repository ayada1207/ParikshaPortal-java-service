package com.exam.javasserverice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("roles")
public class Roles {
	
	 	@Id
	    private String id;
	 	
	    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	    private String role;
	    
		public String getId() {
			return id;
		}
		public String getRole() {
			return role;
		}
		public void setId(String id) {
			this.id = id;
		}
		public void setRole(String role) {
			this.role = role;
		}
	
	    
}
