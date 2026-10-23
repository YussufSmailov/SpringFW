package com.example.shopapi.web;

import com.example.shopapi.config.AppProperties;
import com.example.shopapi.domain.exception.NotFoundException;
import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.service.CustomerService;
import com.example.shopapi.service.CustomerServiceTest;
import com.example.shopapi.service.OrderService;
import com.example.shopapi.web.controller.CustomerController;
import com.example.shopapi.web.mapper.CustomerWebMapper;
import com.example.shopapi.web.mapper.OrderWebMapper;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Slice test: only the web layer starts; the service is a mock. */
@WebMvcTest(CustomerController.class)
@Import({CustomerWebMapper.class, OrderWebMapper.class})
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    OrderService orderService;
    @MockitoBean
    CustomerService customerService;

    @Test
    void getById_returns200() throws Exception {
        when(customerService.findById(1L)).thenReturn(Customer.restore(1L, "Yussuf", "y@kbtu.kz"));

        mockMvc.perform(get("/api/v1/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Yussuf"));
    }

    @Test
    void getById_whenMissing_returns404() throws Exception {
        when(customerService.findById(9L)).thenThrow(new NotFoundException("Customer 9 not found"));

        mockMvc.perform(get("/api/v1/customers/9"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Customer 9 not found"));
    }
}

