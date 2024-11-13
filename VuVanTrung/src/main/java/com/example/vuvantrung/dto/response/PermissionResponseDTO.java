package com.example.vuvantrung.dto.response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PermissionResponseDTO {
    private Long id;
    private String title;
    private String name;
    private Integer status;
    private Boolean deleted;
    private Date createdAt;
    private Date updatedAt;
//    private List<RolePermissionDTO> rolePermissions;
}

