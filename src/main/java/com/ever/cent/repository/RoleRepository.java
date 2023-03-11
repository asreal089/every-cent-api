package com.ever.cent.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ever.cent.domain.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long >{

	Role findByName(String name);
	
}
