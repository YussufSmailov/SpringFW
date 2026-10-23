package com.example.shopapi.service;

import com.example.shopapi.domain.exception.ConflictException;
import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.model.Order;
import com.example.shopapi.domain.repository.CustomerRepository;
import com.example.shopapi.domain.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock OrderRepository orderRepository;
    @Mock CustomerRepository customerRepository;
    @InjectMocks OrderService orderService;

    @Test
    void create_whenCustomerMissing_throws409() {
        when(customerRepository.findById(9L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.create("Mouse", 1, BigDecimal.TEN, 9L))
                .isInstanceOf(ConflictException.class);
        verify(orderRepository, never()).save(any());
    }
}
