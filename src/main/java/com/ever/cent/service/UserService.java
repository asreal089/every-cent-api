package com.ever.cent.service;

import com.ever.cent.domain.dto.SignUpRequest;
import com.ever.cent.domain.model.User;
import com.ever.cent.exception.UserAlreadyExistAuthenticationException;

public interface UserService {

    public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException;

}
