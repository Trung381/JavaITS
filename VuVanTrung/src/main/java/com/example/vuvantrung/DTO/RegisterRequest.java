package com.example.vuvantrung.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

// số not null, chuỗi not empty

public record RegisterRequest(
        @NotEmpty(message = "First name is required")
        String firstname,

        @NotEmpty(message = "Last name is required")
        String lastname,

        @Email(message = "Invalid email format")
        @NotEmpty(message = "Email is required")
        String email,

        @NotEmpty(message = "Username is required")
        @Size(min = 4, message = "Username must be at least 4 characters")
        String username,

        @NotEmpty(message = "Password is required")
        @Size(min = 5, message = "Password must be at least 5 characters")
        String password,

        @NotEmpty(message = "Address is required")
        String address
) {}

//public record RegisterRequest(String firstname, String lastname, String email, String username, String password, String address) {
//}
