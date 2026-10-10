package com.example.shopapi.mapper;

import com.example.shopapi.dto.*;
import com.example.shopapi.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public void copyToModel(CustomerRequest request, Customer customer){
        customer.setName(request.name());
        customer.setEmail(request.email());
    }
    public CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }
}
