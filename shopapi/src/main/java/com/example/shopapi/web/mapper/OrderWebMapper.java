package com.example.shopapi.web.mapper;

import com.example.shopapi.config.AppProperties;
import com.example.shopapi.domain.model.Order;
import com.example.shopapi.web.dto.OrderRequest;
import com.example.shopapi.web.dto.OrderResponse;
import com.example.shopapi.persistence.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class OrderWebMapper {
    private final AppProperties appProperties;

    public OrderResponse toResponse(Order order){
        return new OrderResponse(order.getId(), order.getProduct(), order.getQuantity(),
                order.getPrice(), order.totalPrice(), appProperties.currency(), order.getCustomerId());
    }
}
