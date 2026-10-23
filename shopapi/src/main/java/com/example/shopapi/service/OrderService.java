package com.example.shopapi.service;

import com.example.shopapi.domain.exception.NotFoundException;
import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.model.Order;
import com.example.shopapi.domain.repository.CustomerRepository;
import com.example.shopapi.domain.repository.OrderRepository;
import com.example.shopapi.domain.exception.ConflictException;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Transactional(readOnly = true)
    public Page<Order> findAll(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order " + id + " not found"));
    }

    public Order create(String product, int quantity, BigDecimal price, Long customerId) {
        checkCustomerExists(customerId);
        return orderRepository.save(Order.create(product, quantity, price, customerId));
    }

    public Order update(Long id, String product, int quantity, BigDecimal price, Long customerId){
        Order order = getOrder(id);
        checkCustomerExists(customerId);
        order.update(product, quantity, price, customerId);
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        getOrder(id);
        orderRepository.deleteById(id);
    }

    public Customer checkCustomerExists(Long id){
        return customerRepository.findById(id).orElseThrow(()-> new ConflictException("Customer " + id + " not found"));
    }
    public Order getOrder(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new ConflictException("Order with id - " + id + " not found"));
    }
}
