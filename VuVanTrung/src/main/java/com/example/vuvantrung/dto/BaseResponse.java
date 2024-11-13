package com.example.vuvantrung.dto;

public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private String error;

    // Constructors

    // Constructor mặc định cho thành công mà không có dữ liệu
    private BaseResponse() {
        this.success = true;
        this.message = "Operation successful.";
    }

    // Constructor cho thành công với dữ liệu
    private BaseResponse(T data) {
        this.success = true;
        this.message = "Operation successful.";
        this.data = data;
    }

    // Constructor cho thành công với dữ liệu và thông điệp tùy chỉnh
    private BaseResponse(T data, String message) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    // Constructor cho thất bại với thông báo lỗi
    private BaseResponse(String error, String message) {
        this.success = false;
        this.message = message;
        this.error = error;
    }

    // Getters và Setters

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // Phương thức tĩnh để tạo phản hồi thành công với dữ liệu
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data);
    }

    // Phương thức tĩnh để tạo phản hồi thành công với dữ liệu và thông điệp tùy chỉnh
    public static <T> BaseResponse<T> success(T data, String message) {
        return new BaseResponse<>(data, message);
    }

    // Phương thức tĩnh để tạo phản hồi thất bại với thông báo lỗi và thông điệp
    public static <T> BaseResponse<T> error(String error, String message) {
        return new BaseResponse<>(error, message);
    }

    // Phương thức tĩnh để tạo phản hồi thất bại với thông điệp mặc định
    public static <T> BaseResponse<T> error(String message) {
        return new BaseResponse<>(message, "An error occurred.");
    }
}
