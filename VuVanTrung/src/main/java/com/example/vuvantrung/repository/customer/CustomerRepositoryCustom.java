package com.example.vuvantrung.repository.customer;


import com.example.vuvantrung.entity.Customer;
import java.util.List;
import java.util.Optional;

interface CustomerRepositoryCustom {
    Optional<Customer> getCustomerById(Long id);
    List<Customer> getCustomersByName(String name);
    void deleteCustomersByIds(List<Long> ids);
}
