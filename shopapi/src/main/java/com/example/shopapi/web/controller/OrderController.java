package com.example.shopapi.web.controller;

import com.example.shopapi.domain.model.Order;
import com.example.shopapi.web.dto.OrderRequest;
import com.example.shopapi.web.dto.OrderResponse;
import com.example.shopapi.service.OrderService;
import com.example.shopapi.web.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderWebMapper orderMapper;
    @GetMapping
    public Page<OrderResponse> findAll(@PageableDefault(size = 10, sort="id")Pageable pageable){
        return orderService.findAll(pageable).map(orderMapper::toResponse);
    }
    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id){
        return orderMapper.toResponse(orderService.findById(id));
    }

    @PutMapping("/{id}")
    public OrderResponse update(@PathVariable Long id, @Valid @RequestBody OrderRequest request) {
        return orderMapper.toResponse(orderService.update(id, request.product(), request.quantity(), request.price(), request.customerId()));
    }
    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody OrderRequest request){
        Order created = orderService.create(request.product(), request.quantity(), request.price(), request.customerId());
        OrderResponse body = orderMapper.toResponse(created);
        return ResponseEntity.created(URI.create("/api/v1/orders/" + body.id())).body(body);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
