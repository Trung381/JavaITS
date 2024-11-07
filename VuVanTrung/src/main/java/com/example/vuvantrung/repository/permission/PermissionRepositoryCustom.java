package com.example.vuvantrung.repository.permission;

import com.example.vuvantrung.entity.Permission;
import java.util.List;

interface PermissionRepositoryCustom {
    List<Permission> findPermissionsByCustomCriteria(String someCriteria);
}
