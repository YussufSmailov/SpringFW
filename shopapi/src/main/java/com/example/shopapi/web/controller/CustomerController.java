package com.example.shopapi.web.controller;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.web.dto.CustomerRequest;
import com.example.shopapi.web.dto.CustomerResponse;
import com.example.shopapi.web.dto.OrderResponse;
import com.example.shopapi.service.CustomerService;
import com.example.shopapi.web.mapper.CustomerWebMapper;
import com.example.shopapi.web.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerWebMapper customerMapper;
    private final OrderWebMapper orderMapper;
    //findall findbyid findorders
    @GetMapping
    public Page<CustomerResponse> findAll(@PageableDefault(size = 10, sort ="id") Pageable pageable){
        return customerService.findAll(pageable).map(customerMapper::toResponse);
    }
    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        return customerMapper.toResponse(customerService.findById(id));
    }
    @GetMapping({"/{id}/orders"})
    public Page<OrderResponse> findOrders(@PathVariable Long id, @PageableDefault(size=10, sort="id") Pageable pageable){
        return customerService.findOrders(id, pageable).map(orderMapper::toResponse);
    }
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerRequest request){
        Customer created = customerService.create(request.name(), request.email());
        CustomerResponse body = customerMapper.toResponse(created);
        return ResponseEntity.created(URI.create("/api/v1/customers" + body.id())).body(body);
    }
    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @Valid @RequestBody CustomerRequest customerRequest){
        return customerMapper.toResponse(customerService.update(id, customerRequest.name(), customerRequest.email()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       customerService.delete(id);
       return ResponseEntity.noContent().build();
    }
}
