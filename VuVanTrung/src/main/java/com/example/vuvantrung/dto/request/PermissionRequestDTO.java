package com.example.vuvantrung.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PermissionRequestDTO {
    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Status cannot be null")
    private Integer status;

    private Boolean deleted;
}
