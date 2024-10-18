package com.example.vuvantrung.DTO;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
        boolean success,
        String message,
        HttpStatus status,
        T data
) {
    // Optional: Bạn có thể thêm phương thức tiện ích tùy chỉnh nếu cần.
}


