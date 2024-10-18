package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @PostConstruct
    private void fetchUsersFromJson() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Customer>> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");

        try {
            if (inputStream != null) {
                customers = mapper.readValue(inputStream, typeReference);
            } else {
                throw new IllegalArgumentException("data.json file not found!");
            }
        } catch (Exception e) {
            logger.error("Error loading users", e);
        }
    }

    public List<Customer> getAll() { return customers; }

    public Optional<Customer> findById(Long id) {
        return customers.stream().filter(customer -> customer.getId().equals(id)).findFirst();
    }

    public Customer addUser(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public void deleteById(Long id) {
        customers.removeIf(customer -> customer.getId().equals(id));
    }

    public Customer update(Long id, Customer updatedCustomer) {
        Optional<Customer> userOptional = findById(id);
        if (userOptional.isPresent()) {
            Customer customer = userOptional.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            return customer;
        }
        return null;
    }
}
