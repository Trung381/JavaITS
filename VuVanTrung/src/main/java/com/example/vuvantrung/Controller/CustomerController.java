package com.example.vuvantrung.Controller;

import com.example.vuvantrung.Entity.Customer;
import com.example.vuvantrung.Service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    // dependency injection ví dụ thực tế đây
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllUsers() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable Long id) {
        Optional<Customer> user = customerService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public Customer createUser(@RequestBody Customer customer) {
        return customerService.addUser(customer);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Customer> updateUser(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.update(id, updatedCustomer);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
