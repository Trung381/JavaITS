package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Permission;
import com.example.vuvantrung.entity.Role;
import com.example.vuvantrung.repository.PermissionRepository;
import com.example.vuvantrung.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
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
    public Role assignPermissionToRole(Integer roleId, Integer permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found with id " + permissionId));
        role.getPermissions().add(permission);
        return roleRepository.save(role);
    }

    @Transactional
    public Role removePermissionFromRole(Integer roleId, Integer permissionId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found with id " + roleId));
        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new RuntimeException("Permission not found with id " + permissionId));
        role.getPermissions().remove(permission);
        return roleRepository.save(role);
    }
}
