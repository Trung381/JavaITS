package com.example.vuvantrung.repository.role;


import com.example.vuvantrung.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryCustomImpl implements RoleRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<Role> findRolesByCustomCriteria(String someCriteria) {
        QRole role = QRole.role;

        return queryFactory.selectFrom(role)
                .where(role.name.containsIgnoreCase(someCriteria))
                .fetch();
    }

}
