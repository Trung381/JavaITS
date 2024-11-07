package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Permission;
import com.example.vuvantrung.entity.QRole;
import com.example.vuvantrung.entity.QRolePermission;
import com.example.vuvantrung.entity.Role;
import com.example.vuvantrung.repository.permission.PermissionRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final JPAQueryFactory queryFactory;

    public PermissionService(PermissionRepository permissionRepository, JPAQueryFactory queryFactory) {
        this.permissionRepository = permissionRepository;
        this.queryFactory = queryFactory;
    }

    public Permission createPermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Optional<Permission> getPermissionById(Integer id) {
        return permissionRepository.findById(id);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    @Transactional
    public Permission updatePermission(Integer id, Permission permissionDetails) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found with id " + id));
        permission.setName(permissionDetails.getName());
        permission.setDescription(permissionDetails.getDescription());
        return permission;
    }

    public void deletePermission(Integer id) {
        if (!permissionRepository.existsById(id)) {
            throw new RuntimeException("Permission not found with id " + id);
        }
        permissionRepository.deleteById(id);
    }

    public List<Role> getRolesByPermissionId(Integer permissionId) {
        QRole role = QRole.role;
        QRolePermission rolePermission = QRolePermission.rolePermission;

        return queryFactory.select(role)
                .from(role)
                .innerJoin(rolePermission)
                .on(role.id.eq(rolePermission.roleId))
                .where(rolePermission.permissionId.eq(permissionId))
                .fetch();
    }
}
