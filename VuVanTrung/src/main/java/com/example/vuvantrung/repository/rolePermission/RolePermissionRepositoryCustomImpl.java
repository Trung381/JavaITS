package com.example.vuvantrung.repository.rolePermission;

import com.example.vuvantrung.entity.Permission;
import com.example.vuvantrung.entity.QPermission;
import com.example.vuvantrung.entity.QRolePermission;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class RolePermissionRepositoryCustomImpl implements RolePermissionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public RolePermissionRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Permission> findPermissionsByRoleId(Integer roleId) {
        QPermission permission = QPermission.permission;
        QRolePermission rolePermission = QRolePermission.rolePermission;

        return queryFactory.select(permission)
                .from(permission)
                .innerJoin(rolePermission)
                .on(permission.id.eq(rolePermission.permissionId))
                .where(rolePermission.roleId.eq(roleId))
                .fetch();
    }
}
