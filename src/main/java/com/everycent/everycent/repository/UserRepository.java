package com.everycent.everycent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.everycent.everycent.model.User;

public interface UserRepository extends JpaRepository<User, String>{
    User findByUsername(String username);
}
