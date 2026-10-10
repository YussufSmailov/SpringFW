package com.example.shopapi.repository;

import com.example.shopapi.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    List<Customer> findAll();

    Optional<Customer> findById(Long id);

    boolean existById(Long id);

    boolean existByEmail(String email);

    Customer save(Customer customer);

    void deleteById(Long id);

}
