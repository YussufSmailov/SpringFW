package com.example.shopapi.persistence;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.persistence.entity.CustomerEntity;
import com.example.shopapi.persistence.entity.OrderEntity;
import com.example.shopapi.persistence.jpa.CustomerJpaRepository;
import com.example.shopapi.persistence.jpa.OrderJpaRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.flyway.autoconfigure.FlywayAutoConfiguration;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ImportAutoConfiguration(FlywayAutoConfiguration.class)
@ActiveProfiles("test")
public class OrderJpaRepositoryIntegrationTest {
    @Autowired
    CustomerJpaRepository customerRepository;
    @Autowired
    OrderJpaRepository orderRepository;
    @Test
    void findByCustomerId_returnsOrdersOfThisCustomer(){
        CustomerEntity customer = new CustomerEntity();
        customer.setName("Test");
        customer.setEmail("test@example.com");
        customerRepository.save(customer);

        OrderEntity order = new OrderEntity();
        order.setProduct("Mouse");
        order.setQuantity(1);
        order.setPrice(new BigDecimal("8000"));
        order.setCustomer(customer);
        orderRepository.save(order);

        var page = orderRepository.findByCustomerId(customer.getId(), PageRequest.of(0, 10));

        assertThat(page.getTotalElements()).isEqualTo(1);
        assertThat(page.getContent().get(0).getProduct()).isEqualTo("Mouse");
    }
}


