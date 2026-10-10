package com.example.shopapi.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank @Size(max=100) String product,
        @Min(1) @Max(10) int quantity,
        @NotNull @Positive BigDecimal price,
        @NotNull Long customerId

) {

}
