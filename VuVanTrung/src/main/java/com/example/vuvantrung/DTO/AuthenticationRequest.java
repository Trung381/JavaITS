package com.example.vuvantrung.DTO;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password
) {
}

//public record AuthenticationRequest(String username, String password) {
//}
