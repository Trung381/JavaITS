package com.example.vuvantrung.controller;

import com.example.vuvantrung.entity.Role;
import com.example.vuvantrung.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.ok(createdRole);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Integer id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Integer id, @RequestBody Role roleDetails) {
        Role updatedRole = roleService.updateRole(id, roleDetails);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

//    // Endpoint để gán Permission cho Role
//    @PostMapping("/{roleId}/permissions/{permissionId}")
//    public ResponseEntity<Role> assignPermissionToRole(@PathVariable Integer roleId, @PathVariable Integer permissionId) {
//        Role updatedRole = roleService.assignPermissionToRole(roleId, permissionId);
//        return ResponseEntity.ok(updatedRole);
//    }
//
//    // Endpoint để xoá Permission khỏi Role
//    @DeleteMapping("/{roleId}/permissions/{permissionId}")
//    public ResponseEntity<Role> removePermissionFromRole(@PathVariable Integer roleId, @PathVariable Integer permissionId) {
//        Role updatedRole = roleService.removePermissionFromRole(roleId, permissionId);
//        return ResponseEntity.ok(updatedRole);
//    }
}
