package com.github.amarpreetbhatia.phonenumbers.service;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import com.github.amarpreetbhatia.phonenumbers.entity.PhoneNumber;
import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;
import com.github.amarpreetbhatia.phonenumbers.repository.CustomerRepository;
import com.github.amarpreetbhatia.phonenumbers.repository.PhoneNumberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PhoneNumberSvcImplTest {
    @Mock
    private PhoneNumberRepository phoneNumberRepository;
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private PhoneNumberSvcImpl phoneNumberService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPhoneNumbers() {
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setId(1L);
        phoneNumber1.setNumber("1234567890");
        phoneNumber1.setActive(Boolean.TRUE);

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setId(2L);
        phoneNumber2.setNumber("9876543210");
        phoneNumber2.setActive(Boolean.FALSE);

        // Mock the behavior of the repository
        List<PhoneNumber> phoneNumbers = Arrays.asList(
               phoneNumber1, phoneNumber2
        );
        when(phoneNumberRepository.findAll()).thenReturn(phoneNumbers);

        // Call the service method
        List<PhoneNumberVO> result = phoneNumberService.getAllPhoneNumbers();

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("1234567890", result.get(0).getNumber());
        assertEquals("9876543210", result.get(1).getNumber());

        // Verify that the repository method was called
        verify(phoneNumberRepository, times(1)).findAll();
    }

    @Test
    void testGetPhoneNumberByCustomerId() {
        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setId(2L);
        phoneNumber2.setNumber("9876543210");
        phoneNumber2.setActive(Boolean.FALSE);
        // Mock the behavior of the repository
        List<PhoneNumber> phoneNumbers = Arrays.asList(
                phoneNumber2
        );
        when(phoneNumberRepository.findByCustomer(any())).thenReturn(phoneNumbers);
        Customer customer = new Customer();
        customer.setId(101L);
        customer.setName("abc");
        customer.setPhoneNumbers(Arrays.asList(phoneNumber2));
        when(customerRepository.findById(101L)).thenReturn(Optional.of(customer));
        // Call the service method
        List<PhoneNumberVO> resultFound = phoneNumberService.getPhoneNumberByCustomerId(101L);

        // Verify the result
        assertNotNull(resultFound);
        assertEquals(1, resultFound.size());
        assertEquals("9876543210", resultFound.get(0).getNumber());

        verify(phoneNumberRepository, times(1)).findByCustomer(any());
        verify(customerRepository, times(1)).findById(any());
    }

    @Test
    void testActivatePhoneNumber() {
        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setId(1L);
        phoneNumber1.setNumber("1234567890");
        phoneNumber1.setActive(Boolean.FALSE);
        when(phoneNumberRepository.findByNumber(any())).thenReturn(phoneNumber1);

        doNothing().when(phoneNumberRepository).updatePhoneNumberActivation(anyLong(),anyBoolean());

        phoneNumberService.activatePhoneNumber("1234567890");

        verify(phoneNumberRepository, times(1)).findByNumber(any());
        verify(phoneNumberRepository, times(1)).updatePhoneNumberActivation(anyLong(),anyBoolean());

    }
}