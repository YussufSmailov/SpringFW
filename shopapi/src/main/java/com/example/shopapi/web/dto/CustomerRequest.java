package com.example.shopapi.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

public record CustomerRequest(
        @NotBlank @Size(max = 100) String name,
        @NotBlank @Email String email
) {
}
