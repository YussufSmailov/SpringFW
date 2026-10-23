package com.example.shopapi.persistence.mapper;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.model.Order;
import com.example.shopapi.persistence.entity.CustomerEntity;
import com.example.shopapi.persistence.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderEntityMapper {
    public Order toDomain(OrderEntity entity){
        return Order.restore(entity.getId(), entity.getProduct(), entity.getQuantity(), entity.getPrice(), entity.getId());
    }
    public OrderEntity toEntity(Order order, CustomerEntity customer){
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setProduct(order.getProduct());
        entity.setQuantity(order.getQuantity());
        entity.setPrice(order.getPrice());
        entity.setCustomer(customer);
        return entity;
    }
}
