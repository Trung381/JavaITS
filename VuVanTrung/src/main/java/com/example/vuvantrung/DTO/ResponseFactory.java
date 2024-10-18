package com.example.vuvantrung.DTO;

import org.springframework.http.HttpStatus;

public class ResponseFactory {

    // Singleton response cho thành công mà không có data
    public static final Response<Void> SUCCESS = new Response<>(
            true,
            "Operation successful",
            HttpStatus.OK,
            null
    );

    // Singleton response cho lỗi 404
    public static final Response<Void> NOT_FOUND = new Response<>(
            false,
            "Resource not found",
            HttpStatus.NOT_FOUND,
            null
    );

    // Singleton response cho lỗi 500
    public static final Response<Void> SERVER_ERROR = new Response<>(
            false,
            "Internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR,
            null
    );

    // Method để tạo response thành công với data
    public static <T> Response<T> successWithData(T data) {
        return new Response<>(true, "Operation successful", HttpStatus.OK, data);
    }

    // Method để tạo response lỗi tùy chỉnh với data
    public static <T> Response<T> errorWithData(HttpStatus status, String message, T data) {
        return new Response<>(false, message, status, data);
    }
}