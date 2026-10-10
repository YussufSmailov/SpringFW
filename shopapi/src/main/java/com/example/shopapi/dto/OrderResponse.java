package com.example.shopapi.dto;

import java.math.BigDecimal;

public record OrderResponse(Long id, String product, int quantity, BigDecimal price, String currency, Long customerId) {
}
