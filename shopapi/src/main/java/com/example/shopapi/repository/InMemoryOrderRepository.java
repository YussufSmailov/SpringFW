package com.example.shopapi.repository;
import com.example.shopapi.model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<Long, Order> orders = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }
    @Override
    public List<Order> findByCustomerId(Long id){
        List<Order> result = new ArrayList<>();
        for(Order or : orders.values()){
            if (or.getCustomerId().equals(id)){
                result.add(or);
            }
        }
        return result;
    }
    @Override
    public Optional<Order> findById(Long id){
        return Optional.ofNullable(orders.get(id));
    }
    @Override
    public Order save(Order order){
        if (order.getId() == null){
            order.setId(nextId++);
        }
        orders.put(order.getId(), order);
        return order;
    }
    @Override
    public void deleteById(Long id){
        orders.remove(id);
    }




}
