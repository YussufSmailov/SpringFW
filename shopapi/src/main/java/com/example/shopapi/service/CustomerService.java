package com.example.shopapi.service;

import com.example.shopapi.dto.CustomerRequest;
import com.example.shopapi.dto.CustomerResponse;
import com.example.shopapi.dto.OrderResponse;
import com.example.shopapi.exception.ConflictException;
import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.mapper.CustomerMapper;
import com.example.shopapi.mapper.OrderMapper;
import com.example.shopapi.model.Customer;
import com.example.shopapi.model.Order;
import com.example.shopapi.repository.CustomerRepository;
import com.example.shopapi.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, OrderRepository orderRepository, OrderMapper orderMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }
    @Transactional(readOnly = true)
    public Page<CustomerResponse> findAll(Pageable pageable){
        return customerRepository.findAll(pageable).map(customerMapper::toResponse);
    }
    @Transactional(readOnly = true)
    public CustomerResponse findById(Long id){
        return customerMapper.toResponse(getCustomer(id));
    }
    @Transactional(readOnly = true)
    public Page<OrderResponse> findOrders(Long customerId, Pageable pageable) {
        getCustomer(customerId);
        return orderRepository.findByCustomerId(customerId, pageable).map(orderMapper::toResponse);
    }

    public CustomerResponse create(CustomerRequest request){
        if (customerRepository.existsByEmailIgnoreCase(request.email())){
            throw new ConflictException("Email " + request.email() + "already used");
        }
        Customer customer = new Customer();
        customerMapper.copyToModel(request, customer);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    public CustomerResponse update(CustomerRequest customerRequest, Long id){
        Customer customer = getCustomer(id);
        if(customerRepository.existsByEmailIgnoreCase(customerRequest.email())){
            throw new ConflictException("Email already used");
        }
        customerMapper.copyToModel(customerRequest, customer);
        return customerMapper.toResponse(customerRepository.save(customer));
    }
    public void delete(Long id) {
        getCustomer(id);
        if (!orderRepository.existsByCustomerId(id)){
            throw new ConflictException("Customer can not be deleted");
        }
        customerRepository.deleteById(id);
    }

    private Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer " + id + " not found"));
    }
}
