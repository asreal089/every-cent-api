package com.ever.cent.exception;

import org.springframework.security.core.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserAlreadyExistAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -7505842653150679231L;

	public UserAlreadyExistAuthenticationException(String msg) {
		super(msg);
		log.debug(msg.toString());
	}


}
