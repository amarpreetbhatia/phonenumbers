package com.github.amarpreetbhatia.phonenumbers.repository;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import com.github.amarpreetbhatia.phonenumbers.entity.PhoneNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PhoneNumberRepositoryTest {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindByCustomer() {
        // Given
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer = customerRepository.save(customer);

        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setNumber("1234567890");
        phoneNumber1.setCustomer(customer);
        phoneNumberRepository.save(phoneNumber1);

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setNumber("0987654321");
        phoneNumber2.setCustomer(customer);
        phoneNumberRepository.save(phoneNumber2);

        // When
        List<PhoneNumber> phoneNumbers = phoneNumberRepository.findByCustomer(customer);

        // Then
        assertThat(phoneNumbers).hasSize(2);
        assertThat(phoneNumbers).extracting(PhoneNumber::getNumber).containsExactlyInAnyOrder("1234567890", "0987654321");
    }

    @Test
    public void testFindByNumber() {
        // Given
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("1234567890");
        phoneNumberRepository.save(phoneNumber);

        // When
        PhoneNumber foundPhoneNumber = phoneNumberRepository.findByNumber("1234567890");

        // Then
        assertThat(foundPhoneNumber).isNotNull();
        assertThat(foundPhoneNumber.getNumber()).isEqualTo("1234567890");
    }

    @Test
    @Transactional
    public void testUpdateActiveStatus() {
        // Given
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumber("1234567890");
        phoneNumber.setActive(false);
        phoneNumber = phoneNumberRepository.save(phoneNumber);

        // When
        phoneNumberRepository.updateActiveStatus(true, phoneNumber.getId());
        PhoneNumber updatedPhoneNumber = phoneNumberRepository.findById(phoneNumber.getId()).orElse(null);

        // Then
        assertThat(updatedPhoneNumber).isNotNull();
        assertThat(updatedPhoneNumber.isActive()).isTrue();
    }
}
