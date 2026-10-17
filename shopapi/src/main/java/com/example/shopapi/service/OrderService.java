package com.example.shopapi.service;

import com.example.shopapi.dto.OrderRequest;
import com.example.shopapi.dto.OrderResponse;
import com.example.shopapi.exception.ConflictException;
import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.mapper.OrderMapper;
import com.example.shopapi.model.Customer;
import com.example.shopapi.model.Order;
import com.example.shopapi.repository.CustomerRepository;

import com.example.shopapi.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;
// findAll(),
    @Transactional(readOnly = true)
    public Page<OrderResponse> findAll(Pageable pageable){
        return orderRepository.findAll(pageable).map(orderMapper::toResponse);
    }
    @Transactional(readOnly = true)
    public OrderResponse findById(Long id){
        return orderMapper.toResponse(getOrder(id));
    }
    public OrderResponse create(OrderRequest request) {
        Customer customer = getCustomerForOrder(request.customerId());
        Order order = new Order();
        orderMapper.copyToModel(request, order);
        orderRepository.save(order);
        return orderMapper.toResponse(order);
    }
    public OrderResponse update(Long id, OrderRequest request){
        Order order = getOrder(id);
        Customer customer = getCustomerForOrder(request.customerId());
        orderMapper.copyToModel(request, order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }

    public Order getOrder(Long id){
        return orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order " + id + " not found"));
    }
    private Customer getCustomerForOrder(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new ConflictException("Customer " + customerId + " does not exist"));
    }
}
