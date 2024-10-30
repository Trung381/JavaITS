package com.example.vuvantrung.repository.customer;

//class CustomerRepositoryImpl extends BaseRepository implements CustomerRepositoryCustom{
//
//}


import com.example.vuvantrung.entity.Customer;
import com.example.vuvantrung.entity.QCustomer;
import com.example.vuvantrung.repository.BaseRepository;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl extends BaseRepository implements CustomerRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    protected QCustomer getEntityPath() {
        return QCustomer.customer;
    }

    @Override
    protected NumberPath<Long> getIdPath() {
        return QCustomer.customer.id; // Trả về trường ID của thực thể Customer
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        QCustomer qCustomer = QCustomer.customer;
        Customer customer = queryFactory.selectFrom(qCustomer)
                .where(qCustomer.id.eq(id))
                .fetchOne();
        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        QCustomer qCustomer = QCustomer.customer;
        return queryFactory.selectFrom(qCustomer)
                .where(qCustomer.name.containsIgnoreCase(name))
                .fetch();
    }

    @Override
    public void deleteCustomersByIds(List<Long> ids) {
        deleteByIds(ids); // Sử dụng phương thức xóa chung từ BaseRepository
    }
}
