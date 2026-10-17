package com.example.shopapi.controller;

import com.example.shopapi.dto.OrderRequest;
import com.example.shopapi.dto.OrderResponse;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public Page<OrderResponse> findAll(@PageableDefault(size = 10, sort="id")Pageable pageable){
        return orderService.findAll(pageable);
    }
    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id){
        return orderService.findById(id);
    }

    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Long id, @Valid @RequestBody OrderRequest request) {
        return orderService.update(id, request);
    }
    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request){
        OrderResponse created = orderService.create(request);
        return ResponseEntity.created(URI.create("/api/v1/orders/" + created.id())).body(created);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
