package com.example.vuvantrung.repository.user;

import com.example.vuvantrung.entity.QUser;
import com.example.vuvantrung.entity.Role;
import com.example.vuvantrung.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public List<User> findAllUserWithRole(String role){
        QUser qUser = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();
        if(role != null && !role.isEmpty()){
            builder.and(qUser.role.name.eq(role));
        }

        return queryFactory.selectFrom(qUser)
                .where(builder)
                .fetch();
    }


}
