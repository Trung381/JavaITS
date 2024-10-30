package com.example.vuvantrung.repository;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public abstract class BaseRepository {

    @Autowired
    protected JPAQueryFactory queryFactory;

    // Phương thức trừu tượng để nhận EntityPathBase của thực thể con
    protected abstract EntityPathBase<?> getEntityPath();

    // Phương thức trừu tượng để nhận ID path của thực thể con (ví dụ: QCustomer.customer.id)
    protected abstract NumberPath<Long> getIdPath();

    /**
     * Lấy tất cả các bản ghi cho một thực thể
     */
    public List<?> findAll() {
        return queryFactory.selectFrom(getEntityPath()).fetch();
    }

    /**
     * Đếm số lượng bản ghi của một thực thể
     */
    public long count() {
        return queryFactory.selectFrom(getEntityPath()).fetchCount();
    }

    /**
     * Xóa các bản ghi theo danh sách ID
     */
    public void deleteByIds(List<Long> ids) {
        queryFactory.delete(getEntityPath())
                .where(getIdPath().in(ids))  // Sử dụng getIdPath() để lấy trường ID
                .execute();
    }
}

