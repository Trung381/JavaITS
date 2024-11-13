package com.example.vuvantrung.exception;

import com.example.vuvantrung.dto.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý ngoại lệ UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleUserNotFound(UserNotFoundException ex) {
        log.error("UserNotFoundException: ", ex);
        BaseResponse<Void> response = BaseResponse.error(ex.getMessage(), "User not found.");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Xử lý ngoại lệ InvalidCredentialsException
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<BaseResponse<Void>> handleInvalidCredentials(InvalidCredentialsException ex) {
        log.error("InvalidCredentialsException: ", ex);
        BaseResponse<Void> response = BaseResponse.error(ex.getMessage(), "Invalid credentials.");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Xử lý các lỗi validation (MethodArgumentNotValidException)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex){
        log.error("MethodArgumentNotValidException: ", ex);
        Map<String, String> errors = new HashMap<>();

        // Lấy các lỗi validation theo trường
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        BaseResponse<Map<String, String>> response = BaseResponse.error("Validation failed.", "Invalid input data.");
        // Gán lỗi chi tiết vào data
        response.setData(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Xử lý ngoại lệ AuthenticationException
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<Void>> handleAuthenticationException(AuthenticationException ex){
        log.error("AuthenticationException: ", ex);
        BaseResponse<Void> response = BaseResponse.error("Unauthorized access.", "Authentication failed.");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    // Xử lý các ngoại lệ khác
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGeneralException(Exception ex) {
        log.error("Exception: ", ex);
        BaseResponse<Void> response = BaseResponse.error("Internal Server Error", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof java.time.format.DateTimeParseException) {
            return new ResponseEntity<>("Invalid date format. Please use 'yyyy-MM-dd'.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Invalid request data.", HttpStatus.BAD_REQUEST);
    }
}
