package com.example.vuvantrung.repository.permission;

import com.example.vuvantrung.entity.Permission;
import com.example.vuvantrung.entity.QPermission;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionRepositoryCustomImpl implements PermissionRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<Permission> findPermissionsByCustomCriteria(String someCriteria) {
        QPermission permission = QPermission.permission;

        return queryFactory.selectFrom(permission)
                .where(permission.name.containsIgnoreCase(someCriteria))
                .fetch();
    }
}
