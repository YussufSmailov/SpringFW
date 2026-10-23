package com.example.shopapi.persistence.jpa;

import com.example.shopapi.persistence.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
    Page<OrderEntity> findByCustomerId(Long customerId, Pageable pageable);
    boolean existsByCustomerId(Long customerId);
}
