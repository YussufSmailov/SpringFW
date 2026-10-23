package com.example.shopapi.persistence.adapter;

import com.example.shopapi.domain.model.Customer;
import com.example.shopapi.domain.repository.CustomerRepository;
import com.example.shopapi.persistence.jpa.CustomerJpaRepository;
import com.example.shopapi.persistence.mapper.CustomerEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerRepositoryAdapter implements CustomerRepository {
    private final CustomerJpaRepository jpaRepository;
    private final CustomerEntityMapper mapper;
    public CustomerRepositoryAdapter(CustomerJpaRepository jpaRepository, CustomerEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Page<Customer> findAll(Pageable pageable){
        return jpaRepository.findAll(pageable).map(mapper::toDomain);
    }
    @Override
    public Optional<Customer> findById(Long id){
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
    @Override
    public boolean existsById(Long id){
        return jpaRepository.existsById(id);
    }
    @Override
    public boolean existsByEmail(String email){
        return jpaRepository.existsByEmailIgnoreCase(email);
    }
    @Override
    public Customer save(Customer customer){
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(customer)));
    }
    @Override
    public void deleteById(Long id){
        jpaRepository.deleteById(id);
    }
}
