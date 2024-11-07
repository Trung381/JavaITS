package com.example.vuvantrung.repository.rolePermission;

import com.example.vuvantrung.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>, RolePermissionRepositoryCustom {
    Optional<RolePermission> findByRoleIdAndPermissionId(Integer roleId, Integer permissionId);
}



