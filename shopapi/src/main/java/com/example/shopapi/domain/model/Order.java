package com.example.shopapi.domain.model;

import java.math.BigDecimal;

public class Order {
    private final Long id;
    private String product;
    private int quantity;
    private BigDecimal price;
    private Long customerId;
    private Order(Long id, String product, int quantity, BigDecimal price, Long customerId) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.customerId = customerId;
    }
    public static Order create(String product, int quantity, BigDecimal price, Long customerId){
        return new Order(null, product, quantity, price, customerId);
    }
    public static Order restore(Long id, String product, int quantity, BigDecimal price, Long customerId){
        return new Order(id, product, quantity, price, customerId);
    }
    public void update(String product,int quantity, BigDecimal price, Long customerId){
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.customerId = customerId;
    }
    public BigDecimal totalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Long getId() { return id; }
    public String getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }
    public Long getCustomerId() { return customerId; }
}
