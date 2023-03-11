package com.ever.cent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ever.cent.domain.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	boolean existsByEmail(String email);
	
}
