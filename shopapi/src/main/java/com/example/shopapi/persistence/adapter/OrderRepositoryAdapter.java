package com.example.shopapi.persistence.adapter;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.model.Order;
import com.example.shopapi.domain.repository.CustomerRepository;
import com.example.shopapi.domain.repository.OrderRepository;
import com.example.shopapi.persistence.entity.CustomerEntity;
import com.example.shopapi.persistence.jpa.CustomerJpaRepository;
import com.example.shopapi.persistence.jpa.OrderJpaRepository;
import com.example.shopapi.persistence.mapper.OrderEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderEntityMapper mapper;
    private final OrderJpaRepository jpaRepository;
    private final CustomerJpaRepository customerJpaRepository;
    @Override
    public Page<Order> findAll(Pageable pageable){
        return jpaRepository.findAll(pageable).map(mapper::toDomain);
    }
    @Override
    public Optional<Order> findById(Long id){
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
    @Override
    public Page<Order> findByCustomerId(Long customerId, Pageable pageable){
        return jpaRepository.findByCustomerId(customerId, pageable).map(mapper::toDomain);
    }

    @Override
    public boolean existsByCustomerId(Long customerId) {
        return jpaRepository.existsByCustomerId(customerId);
    }
    @Override
    public Order save(Order order){
        CustomerEntity customer = customerJpaRepository.getReferenceById(order.getCustomerId());
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(order, customer)));
    }
    @Override
    public void deleteById(Long id){
        jpaRepository.deleteById(id);
    }
}
