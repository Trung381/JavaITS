package com.example.vuvantrung.repository.role;

import com.example.vuvantrung.entity.Role;
import java.util.List;

interface RoleRepositoryCustom {
    List<Role> findRolesByCustomCriteria(String someCriteria);
}
