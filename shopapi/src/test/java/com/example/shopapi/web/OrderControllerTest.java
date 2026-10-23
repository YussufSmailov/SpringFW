package com.example.shopapi.web;

import com.example.shopapi.config.AppProperties;
import com.example.shopapi.domain.model.Order;
import com.example.shopapi.service.OrderService;
import com.example.shopapi.web.controller.OrderController;
import com.example.shopapi.web.mapper.OrderWebMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import(OrderWebMapper.class)
class OrderControllerTest {

    @Autowired MockMvc mockMvc;
    @MockitoBean OrderService orderService;
    @MockitoBean AppProperties appProperties;

    @Test
    void getById_returnsOrderWithTotal() throws Exception {
        when(appProperties.currency()).thenReturn("KZT");
        when(orderService.findById(1L)).thenReturn(Order.restore(1L, "Mouse", 2, new BigDecimal("8000"), 1L));

        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(16000))
                .andExpect(jsonPath("$.currency").value("KZT"));
    }
}
