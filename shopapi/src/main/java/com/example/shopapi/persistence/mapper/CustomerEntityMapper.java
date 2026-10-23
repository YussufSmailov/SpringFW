package com.example.shopapi.persistence.mapper;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.persistence.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityMapper{
    public Customer toDomain(CustomerEntity entity){
        return Customer.restore(entity.getId(), entity.getName(), entity.getEmail());
    }
    public CustomerEntity toEntity(Customer customer){
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        return entity;
    }
}
