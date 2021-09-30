package com.ever.cent.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;

import com.ever.cent.domain.dto.LocalUser;
import com.ever.cent.domain.dto.SignUpRequest;
import com.ever.cent.domain.model.User;
import com.ever.cent.exception.UserAlreadyExistAuthenticationException;

public interface UserService {
	 
    public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;
 
    User findUserByEmail(String email);
 
    Optional<User> findUserById(Long id);
 
    LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}