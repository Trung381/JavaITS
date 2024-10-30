package com.example.vuvantrung.repository.customer;

import com.example.vuvantrung.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {
    boolean existsByEmail(String email);
}

