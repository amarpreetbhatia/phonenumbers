package com.github.amarpreetbhatia.phonenumbers.controller;

import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;
import com.github.amarpreetbhatia.phonenumbers.service.PhoneNumberSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
        LOGGER.info("PhoneNumberController::getAllPhoneNumbers");
        return phoneNumberSvc.getAllPhoneNumbers();
    }

    @GetMapping("/customer/{customerId}")
    public List<PhoneNumberVO> getPhoneNumbersForCustomer(@PathVariable Long customerId) {
        LOGGER.info("PhoneNumberController::getPhoneNumbersForCustomer for customerId {}", customerId);
        return phoneNumberSvc.getPhoneNumberByCustomerId(customerId);
    }

    @PatchMapping("/{phoneNumber}/activate")
    public void activatePhoneNumber(@PathVariable String phoneNumber) {
        LOGGER.info("PhoneNumberController::activatePhoneNumber for phoneNumber {}", phoneNumber);
        phoneNumberSvc.activatePhoneNumber(phoneNumber);
    }
}
