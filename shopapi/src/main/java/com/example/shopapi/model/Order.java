package com.example.shopapi.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Order {
    private Long id;
    private String product;
    private int quantity;
    private BigDecimal price;
    private Long customerId;
}
