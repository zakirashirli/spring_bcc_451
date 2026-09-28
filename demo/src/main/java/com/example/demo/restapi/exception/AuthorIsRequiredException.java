package com.example.demo.restapi.exception;

public class AuthorIsRequiredException extends RuntimeException {
    public AuthorIsRequiredException(String message) {
        super(message);
    }
}
