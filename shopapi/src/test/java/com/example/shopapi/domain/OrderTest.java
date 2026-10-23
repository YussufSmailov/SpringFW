package com.example.shopapi.domain;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.model.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class OrderTest {
    @Test
    void totalPrice_isPriceTimesQuantity(){
        Order order = Order.create("mouse", 3, new BigDecimal("8000"), 1L);
        assertThat(order.totalPrice()).isEqualByComparingTo("24000");
    }
    @Test
    void newCustomer_hasNoId_andLowerCaseEmail(){
        Customer customer = Customer.create("Dana", "Dana@Kbtu.KZ");
        assertThat(customer.getId()).isNull();
        assertThat(customer.getEmail()).isEqualTo("dana@kbtu.kz");
        assertThat(customer.hasEmail("DANA@kbtu.kz")).isTrue();
    }
}
