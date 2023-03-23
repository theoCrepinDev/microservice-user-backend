package fr.crepin.microserviceuserbackend.exception;

import org.springframework.security.core.AuthenticationException;

public class RegisterUserException extends AuthenticationException {
    public RegisterUserException(String message) {
        super(message);
    }
}
