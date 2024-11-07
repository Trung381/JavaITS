package com.example.vuvantrung.repository.rolePermission;

import com.example.vuvantrung.entity.Permission;

import java.util.List;

public interface RolePermissionRepositoryCustom {
    List<Permission> findPermissionsByRoleId(Integer roleId);
}