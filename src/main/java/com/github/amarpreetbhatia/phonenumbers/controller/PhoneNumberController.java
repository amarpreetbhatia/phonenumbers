package com.github.amarpreetbhatia.phonenumbers.controller;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import com.github.amarpreetbhatia.phonenumbers.entity.PhoneNumber;
import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;
import com.github.amarpreetbhatia.phonenumbers.repository.CustomerRepository;
import com.github.amarpreetbhatia.phonenumbers.repository.PhoneNumberRepository;
import com.github.amarpreetbhatia.phonenumbers.service.PhoneNumberSvc;
import com.github.amarpreetbhatia.phonenumbers.service.PhoneNumberSvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/phoneNumbers")
public class PhoneNumberController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberController.class.getName());

    private final PhoneNumberSvc phoneNumberSvc;
    public PhoneNumberController(PhoneNumberSvc phoneNumberSvc) {
       this.phoneNumberSvc = phoneNumberSvc;
    }

    @GetMapping
    public List<PhoneNumberVO> getAllPhoneNumbers() {
        return phoneNumberSvc.getAllPhoneNumbers();
    }

    @GetMapping("/customer/{customerId}")
    public List<PhoneNumberVO> getPhoneNumbersForCustomer(@PathVariable Long customerId) {

        return phoneNumberSvc.getPhoneNumberByCustomerId(customerId);
    }

    @PostMapping("/{phoneNumber}/activate")
    public void activatePhoneNumber(@PathVariable String phoneNumber) {
        phoneNumberSvc.activatePhoneNumber(phoneNumber);
    }
}
