package com.example.shopapi.service;

import com.example.shopapi.dto.OrderRequest;
import com.example.shopapi.dto.OrderResponse;
import com.example.shopapi.exception.ConflictException;
import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.mapper.OrderMapper;
import com.example.shopapi.model.Order;
import com.example.shopapi.repository.CustomerRepository;
import com.example.shopapi.repository.InMemoryCustomerRepository;
import com.example.shopapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public List<OrderResponse> findAll(){
        List<OrderResponse> result = new ArrayList<>();
        for(Order order : orderRepository.findAll()){
            result.add(orderMapper.toResponse(order));
        }
        return result;
    }
    public OrderResponse findById(Long id){
        return orderMapper.toResponse(getOrder(id));
    }
    public OrderResponse create(OrderRequest request){
        checkCustomerExists(request.customerId());
        Order order = new Order();
        orderMapper.copyToModel(request, order);
        return orderMapper.toResponse(orderRepository.save(order));
    }
    public OrderResponse update(Long id, OrderRequest request){
        Order order = getOrder(id);
        checkCustomerExists(id);
        orderMapper.copyToModel(request, order);
        return orderMapper.toResponse(orderRepository.save(order));
    }

    public void delete(Long id){
        orderRepository.deleteById(id);
    }

    public Order getOrder(Long id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Order " + id + " not found"));
    }
    private void checkCustomerExists(Long customerId) {
        if (!customerRepository.existById(customerId)) {
            throw new ConflictException("Customer " + customerId + " does not exist");
        }
    }
}
