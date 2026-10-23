package com.example.shopapi.service;

import com.example.shopapi.domain.exception.ConflictException;
import com.example.shopapi.domain.exception.NotFoundException;
import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.repository.CustomerRepository;
import com.example.shopapi.domain.repository.OrderRepository;
import com.example.shopapi.persistence.adapter.CustomerRepositoryAdapter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springdoc.core.customizers.ActuatorOpenApiCustomizer;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    OrderRepository orderRepository;
    @InjectMocks
    CustomerService customerService;

    @Test
    void create_savesCustomerWithLowerCaseEmail(){
        when(customerRepository.existsByEmail("Dana@Kbtu.kz")).thenReturn(false);
        customerService.create("Dana", "Dana@Kbtu.kz");
        ArgumentCaptor<Customer> saved = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).save(saved.capture());
        assertThat(saved.getValue().getEmail()).isEqualTo("dana@kbtu.kz");
    }
    @Test
    void create_whenEmailTaken_throws409(){
        when(customerRepository.existsByEmail("dana@kbtu.kz")).thenReturn(true);
        assertThatThrownBy(() -> customerService.create("Dana", "dana@kbtu.kz")).isInstanceOf(ConflictException.class);
        verify(customerRepository, never()).save(any());
    }
    @Test
    void findById_whenMissing_throws404() {
        when(customerRepository.findById(9L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerService.findById(9L)).isInstanceOf(NotFoundException.class);
    }
}




