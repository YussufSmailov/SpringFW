package com.example.shopapi.service;

import com.example.shopapi.domain.exception.NotFoundException;
import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.model.Order;
import com.example.shopapi.domain.repository.CustomerRepository;
import com.example.shopapi.domain.repository.OrderRepository;
import com.example.shopapi.domain.exception.ConflictException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer " + id + " not found"));
    }
    @Transactional(readOnly = true)
    public Page<Order> findOrders(Long customerId, Pageable pageable){
        getCustomer(customerId);
        return orderRepository.findByCustomerId(customerId, pageable);
    }
    public Customer create(String name, String email){
        if (customerRepository.existsByEmail(email)){
            throw  new ConflictException("User with email - " + email + " already exists");
        }
        return customerRepository.save(Customer.create(name, email));
    }
    public Customer update(Long id, String name, String email){
        Customer customer = getCustomer(id);
        if (customerRepository.existsByEmail(email)){
            throw  new ConflictException("User with email - " + email + " already exists");
        }
        customer.update(name, email);
        return customerRepository.save(customer);
    }
    public void delete(Long id){
        getCustomer(id);
        if (orderRepository.existsByCustomerId(id)){
            throw new ConflictException("User with id - " + id + "has order and can not be deleted");
        }
        customerRepository.deleteById(id);
    }

    public Customer getCustomer(Long id){
        return customerRepository.findById(id).orElseThrow(()-> new ConflictException("Customer " + id + " not found"));
    }

}
