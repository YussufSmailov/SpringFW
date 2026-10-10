package com.example.shopapi.repository;

import com.example.shopapi.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.*;

@Component
public class InMemoryCustomerRepository implements CustomerRepository {
    private final Map<Long, Customer> customers = new HashMap<>();
    private long nextId = 1;
    @Override
    public List<Customer> findAll(){
        return new ArrayList<>(customers.values());
    }
    @Override
    public Optional<Customer> findById(Long id){
        return Optional.ofNullable(customers.get(id));
    }
    @Override
    public boolean existById(Long id){
        return customers.containsKey(id);
    }
    @Override
    public boolean existByEmail(String email){
        for(Customer customer: customers.values()){
            if (customer.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }
    @Override
    public Customer save(Customer customer){
        if (customer.getId() == null){
            customer.setId(nextId++);
        }
        customers.put(customer.getId(), customer);
        return customer;
    }
    @Override
    public void deleteById(Long id){
        customers.remove(id);
    }

}
