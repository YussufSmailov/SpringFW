package com.example.shopapi.web.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank @Size(max=100) String product,
        @Min(1) @Max(10) int quantity,
        @NotNull @Positive @Digits(integer = 10, fraction = 2) BigDecimal price,
        @NotNull Long customerId

) {

}
