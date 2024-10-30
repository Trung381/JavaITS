package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Customer;
import com.example.vuvantrung.repository.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Lấy thông tin khách hàng theo ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.getCustomerById(id);
    }

    // Lấy danh sách khách hàng theo tên
    public List<Customer> getCustomersByName(String name) {
        return customerRepository.getCustomersByName(name);
    }

    // Thêm mới hoặc cập nhật khách hàng
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    // Xóa khách hàng theo danh sách ID
    public void deleteCustomersByIds(List<Long> ids) {
        customerRepository.deleteCustomersByIds(ids);
    }

    // Lấy tất cả khách hàng
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    // Kiểm tra sự tồn tại của khách hàng bằng email
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
}
