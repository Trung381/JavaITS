package com.example.vuvantrung.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
        log.warn(message);
    }
}
