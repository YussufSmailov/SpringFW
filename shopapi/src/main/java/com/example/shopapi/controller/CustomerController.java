package com.example.shopapi.controller;

import com.example.shopapi.dto.CustomerRequest;
import com.example.shopapi.dto.CustomerResponse;
import com.example.shopapi.dto.OrderResponse;
import com.example.shopapi.service.CustomerService;
import com.example.shopapi.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    //findall findbyid findorders
    @GetMapping
    public Page<CustomerResponse> findAll(@PageableDefault(size = 10, sort ="id") Pageable pageable){
        return customerService.findAll(pageable);
    }
    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        return (customerService.findById(id));
    }a
    @GetMapping({"/{id}/orders"})
    public Page<OrderResponse> findOrders(@PathVariable Long id, @PageableDefault(size=10, sort="id") Pageable pageable){
        return customerService.findOrders(id, pageable);
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
