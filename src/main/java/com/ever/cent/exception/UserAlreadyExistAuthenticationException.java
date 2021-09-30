package com.ever.cent.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -7505842653150679231L;

	public UserAlreadyExistAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}


}
