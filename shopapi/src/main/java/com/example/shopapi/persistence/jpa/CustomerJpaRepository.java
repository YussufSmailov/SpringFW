package com.example.shopapi.persistence.jpa;

import com.example.shopapi.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {


    boolean existsByEmailIgnoreCase(String email);

}
