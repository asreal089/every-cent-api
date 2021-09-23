package com.everycent.everycent.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.everycent.everycent.model.VerificationToken;


@Repository
public interface VerificationTokenRepository extends CrudRepository<VerificationToken, String> {
    VerificationToken findByToken(String token);
}
