package com.example.shopapi.mapper;

import com.example.shopapi.config.AppProperties;
import com.example.shopapi.dto.OrderRequest;
import com.example.shopapi.dto.OrderResponse;
import com.example.shopapi.model.Order;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.stereotype.Component;
@RequiredArgsConstructor
@Component
public class OrderMapper {
    private final AppProperties appProperties;
    public void copyToModel(OrderRequest request, Order order){
        order.setProduct(request.product());
        order.setPrice(request.price());
        order.setQuantity(request.quantity());
        order.setCustomerId(request.customerId());
    }
    public OrderResponse toResponse(Order order){
        return new OrderResponse(order.getId(), order.getProduct(), order.getQuantity(),
                order.getPrice(), appProperties.currency(), order.getCustomerId());
    }
}
