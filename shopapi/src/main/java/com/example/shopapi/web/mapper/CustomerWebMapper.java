package com.example.shopapi.web.mapper;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.persistence.entity.CustomerEntity;
import com.example.shopapi.web.dto.CustomerRequest;
import com.example.shopapi.web.dto.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerWebMapper {
    public CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }
}
