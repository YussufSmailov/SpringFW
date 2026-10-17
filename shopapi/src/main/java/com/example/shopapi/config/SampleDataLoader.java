package com.example.shopapi.config;

import com.example.shopapi.dto.CustomerRequest;
import com.example.shopapi.dto.CustomerResponse;
import com.example.shopapi.dto.OrderRequest;
import com.example.shopapi.service.CustomerService;
import com.example.shopapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@ConditionalOnProperty(name = "app.seed-data", havingValue = "True")
@RequiredArgsConstructor
public class SampleDataLoader implements ApplicationRunner {
    private final CustomerService customerService;
    private final OrderService orderService;
    @Override
    public void run(ApplicationArguments args) {
        CustomerResponse aliya = customerService.create(new CustomerRequest("Aliya Nurlanova", "aliya@kbtu.kz"));
        CustomerResponse timur = customerService.create(new CustomerRequest("Timur Sadykov", "timur@kbtu.kz"));

        orderService.create(new OrderRequest("Laptop", 1, new BigDecimal("450000"), aliya.id()));
        orderService.create(new OrderRequest("Mouse", 2, new BigDecimal("8000"), aliya.id()));
        orderService.create(new OrderRequest("Monitor", 1, new BigDecimal("120000"), timur.id()));
    }
}
