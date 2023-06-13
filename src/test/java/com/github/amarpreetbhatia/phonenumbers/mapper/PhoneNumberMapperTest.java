package com.github.amarpreetbhatia.phonenumbers.mapper;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import com.github.amarpreetbhatia.phonenumbers.entity.PhoneNumber;
import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberMapperTest {

    @Test
    void toPhoneNumberVO() {
        Customer customer = new Customer();
        customer.setId(101L);
        customer.setName("ABC");
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(2L);
        phoneNumber.setNumber("9876543210");
        phoneNumber.setActive(Boolean.FALSE);
        phoneNumber.setCustomer(customer);
        PhoneNumberVO phoneNumberVO = PhoneNumberMapper
                .toPhoneNumberVO(phoneNumber);
        assertEquals(phoneNumber.getId(),phoneNumberVO.getId());
        assertEquals(phoneNumber.getNumber(),phoneNumberVO.getNumber());
        assertEquals(phoneNumber.getActive(),phoneNumberVO.isActive());
        assertEquals(phoneNumber.getCustomer().getId(),phoneNumberVO.getCustomerId());
    }

    @Test
    void toPhoneNumberVOList() {
        Customer customer = new Customer();
        customer.setId(101L);
        customer.setName("ABC");
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setId(2L);
        phoneNumber.setNumber("9876543210");
        phoneNumber.setActive(Boolean.FALSE);
        phoneNumber.setCustomer(customer);
        List<PhoneNumberVO> phoneNumberVO = PhoneNumberMapper
                .toPhoneNumberVOList(Arrays.asList(phoneNumber));
        assertEquals(1, phoneNumberVO.size());
        phoneNumberVO.forEach(numberVO -> {
            assertEquals(phoneNumber.getId(),numberVO.getId());
            assertEquals(phoneNumber.getNumber(),numberVO.getNumber());
            assertEquals(phoneNumber.getActive(),numberVO.isActive());
            assertEquals(phoneNumber.getCustomer().getId(),numberVO.getCustomerId());
        });
    }
}