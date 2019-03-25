package com.activeledger.health.utility;

public class CredentialException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CredentialException(String msg) {
        this(msg, null);
    }

    public CredentialException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
