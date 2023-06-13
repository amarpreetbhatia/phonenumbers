package com.github.amarpreetbhatia.phonenumbers.repository;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAll();
}
