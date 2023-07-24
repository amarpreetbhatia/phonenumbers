package com.github.amarpreetbhatia.phonenumbers.repository;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindAll() {
        // Given
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setName("Jane Doe");
        customerRepository.save(customer2);

        // When
        List<Customer> customers = customerRepository.findAll();

        // Then
        assertThat(customers).hasSize(2);
        assertThat(customers).extracting(Customer::getName).containsExactlyInAnyOrder("John Doe", "Jane Doe");
    }
}
