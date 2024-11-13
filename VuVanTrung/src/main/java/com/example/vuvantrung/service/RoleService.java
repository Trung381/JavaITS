package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.*;
import com.example.vuvantrung.repository.permission.PermissionRepository;
import com.example.vuvantrung.repository.role.RoleRepository;
import com.example.vuvantrung.repository.rolePermission.RolePermissionRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final JPAQueryFactory queryFactory;
    private final RolePermissionRepository rolePermissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository,
                       JPAQueryFactory queryFactory, RolePermissionRepository rolePermissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.queryFactory = queryFactory;
        this.rolePermissionRepository = rolePermissionRepository;
    }


    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public Role updateRole(Integer id, Role roleDetails) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + id));
        role.setName(roleDetails.getName());
        return role;
    }

    public void deleteRole(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id " + id);
        }
        roleRepository.deleteById(id);
    }

    @Transactional
    public void assignPermissionToRole(Integer roleId, Integer permissionId) {
        // Kiểm tra sự tồn tại của Role và Permission
        if (!roleRepository.existsById(roleId)) {
            throw new RuntimeException("Role not found with id " + roleId);
        }
        if (!permissionRepository.existsById(permissionId)) {
            throw new RuntimeException("Permission not found with id " + permissionId);
        }

        RolePermission rolePermission = new RolePermission(roleId, permissionId);
        rolePermissionRepository.save(rolePermission);
    }

    @Transactional
    public void removePermissionFromRole(Integer roleId, Integer permissionId) {
        RolePermission rolePermission = rolePermissionRepository
                .findByRoleIdAndPermissionId(roleId, permissionId)
                .orElseThrow(() -> new RuntimeException("Relation not found"));

        rolePermissionRepository.delete(rolePermission);
    }


    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        return rolePermissionRepository.getPermissionsByRoleId(roleId);
    }
}
