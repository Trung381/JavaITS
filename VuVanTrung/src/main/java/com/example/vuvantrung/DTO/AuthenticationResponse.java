package com.example.vuvantrung.DTO;

import com.example.vuvantrung.entity.User;

import java.util.HashMap;
import java.util.Map;

public record AuthenticationResponse(
        String token,
        Map<String, String> details,
        String message,
        int http_status)
{
    public static AuthenticationResponse fromUser (String token, User user, String message, int http_status) {
        Map<String, String> details = new HashMap<>();
        details.put("username", user.getUsername());
        details.put("email", user.getEmail());
        return new AuthenticationResponse(token, details, message, http_status);
    }
}
