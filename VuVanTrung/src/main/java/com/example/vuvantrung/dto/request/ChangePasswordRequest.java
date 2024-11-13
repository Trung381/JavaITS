package com.example.vuvantrung.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Current password is required") String currentPassword,
        @NotBlank(message = "New password is required") String newPassword
){ }