package com.example.vuvantrung.DTO;

import org.springframework.http.HttpStatus;

public class ApiResponseFactory {

    // Singleton response cho thành công mà không có data
    public static final ApiResponse<Void> SUCCESS = new ApiResponse<>(
            true,
            "Operation successful",
            HttpStatus.OK,
            null
    );

    // Singleton response cho lỗi 404
    public static final ApiResponse<Void> NOT_FOUND = new ApiResponse<>(
            false,
            "Resource not found",
            HttpStatus.NOT_FOUND,
            null
    );

    // Singleton response cho lỗi 500
    public static final ApiResponse<Void> SERVER_ERROR = new ApiResponse<>(
            false,
            "Internal server error",
            HttpStatus.INTERNAL_SERVER_ERROR,
            null
    );

    // Method để tạo response thành công với data
    public static <T> ApiResponse<T> successWithData(T data) {
        return new ApiResponse<>(true, "Operation successful", HttpStatus.OK, data);
    }

    // Method để tạo response lỗi tùy chỉnh với data
    public static <T> ApiResponse<T> errorWithData(HttpStatus status, String message, T data) {
        return new ApiResponse<>(false, message, status, data);
    }
}