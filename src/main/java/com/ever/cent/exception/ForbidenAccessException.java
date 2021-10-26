package com.ever.cent.exception;

public class ForbidenAccessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ForbidenAccessException(String message) {
        super(message);
    }
 
    public ForbidenAccessException(String message, Throwable cause) {
        super(message, cause);
    }

}
