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
import org.springframework.stereotype.Service;

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
    public List<CustomerResponse> findAll(){
        List<CustomerResponse> result = new ArrayList<>();
        for(Customer customer: customerRepository.findAll()){
            result.add(customerMapper.toResponse(customer));
        }
        return result;
    }
    public List<OrderResponse> findOrders(Long id){
        getCustomer(id);
        List<OrderResponse> result = new ArrayList<>();
        for(Order order: orderRepository.findAll() ){
            if (order.getCustomerId() == id){
                result.add(orderMapper.toResponse(order));
            }
        }
        return  result;
    }
    public CustomerResponse findById(Long id){
        return customerMapper.toResponse(customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer not Found")));
    }


    public CustomerResponse create(CustomerRequest request){
        if (customerRepository.existByEmail(request.email())){
            throw new ConflictException("Email " + request.email() + "already used");
        }
        Customer customer = new Customer();
        customerMapper.copyToModel(request, customer);
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    public CustomerResponse update(CustomerRequest customerRequest, Long id){
        Customer customer = getCustomer(id);
        boolean emailChanged = !customer.getEmail().equalsIgnoreCase(customerRequest.email());
        if (emailChanged && customerRepository.existByEmail(customerRequest.email())) {
            throw new ConflictException("E-mail " + customerRequest.email() + " is already used");
        }
        customerMapper.copyToModel(customerRequest, customer);
        return customerMapper.toResponse(customerRepository.save(customer));
    }
    public void delete(Long id){
        getCustomer(id);
        if (!orderRepository.findByCustomerId(id).isEmpty()) {
            throw new ConflictException("Customer " + id + " has orders and cannot be deleted");
        }
        customerRepository.deleteById(id);
    }

    private Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer " + id + " not found"));
    }
}
