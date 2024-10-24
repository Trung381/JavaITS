package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Permission;
import com.example.vuvantrung.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
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
}
