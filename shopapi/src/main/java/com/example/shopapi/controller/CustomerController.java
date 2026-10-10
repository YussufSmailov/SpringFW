package com.example.shopapi.controller;

import com.example.shopapi.dto.CustomerRequest;
import com.example.shopapi.dto.CustomerResponse;
import com.example.shopapi.dto.OrderResponse;
import com.example.shopapi.service.CustomerService;
import com.example.shopapi.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final OrderService orderService;
    @GetMapping
    public List<CustomerResponse> findAll(){
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        return (customerService.findById(id));
    }
    @GetMapping({"/{id}/orders"})
    public List<OrderResponse> findOrders(@PathVariable Long id){
        return customerService.findOrders(id);
    }
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerRequest request){
        CustomerResponse created = customerService.create(request);
        return ResponseEntity.created(URI.create("/api/v1/customers" + created.id())).body(created);
    }
    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id, @Valid @RequestBody CustomerRequest customerRequest){
        return customerService.update(customerRequest, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
       customerService.delete(id);
       return ResponseEntity.noContent().build();
    }
}
