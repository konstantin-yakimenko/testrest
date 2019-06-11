package com.testres.testrest.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author konst
 */
public class InvalidJwtAuthenticationException extends AuthenticationException {
    public InvalidJwtAuthenticationException(String msg) {
        super(msg);
    }
}
