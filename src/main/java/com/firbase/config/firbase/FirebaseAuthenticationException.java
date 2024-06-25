package com.firbase.config.firbase;

import org.springframework.security.core.AuthenticationException;

public class FirebaseAuthenticationException extends AuthenticationException {

    public FirebaseAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
