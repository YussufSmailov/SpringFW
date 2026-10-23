package com.example.shopapi.domain.repository;

import com.example.shopapi.domain.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderRepository {
    Page<Order> findAll(Pageable pageable);
    Optional<Order> findById(Long id);
    Page<Order> findByCustomerId(Long customerId, Pageable pageable);
    boolean existsByCustomerId(Long customerId);
    Order save(Order order);
    void deleteById(Long id);
}
