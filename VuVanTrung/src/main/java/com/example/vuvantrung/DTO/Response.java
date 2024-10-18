package com.example.vuvantrung.DTO;

import org.springframework.http.HttpStatus;

public record Response<T>(
        boolean success,
        String message,
        HttpStatus status,
        T data
) {

}


