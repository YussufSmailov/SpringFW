package com.example.shopapi.domain.repository;

import com.example.shopapi.domain.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerRepository {
    Page<Customer> findAll(Pageable pageable);
    Optional<Customer> findById(Long id);
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    Customer save(Customer customer);
    void deleteById(Long id);
}
