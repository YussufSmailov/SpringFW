package com.example.shopapi.web.dto;

import java.math.BigDecimal;

public record OrderResponse(Long id, String product, int quantity, BigDecimal price, BigDecimal total, String currency, Long customerId) {
}
