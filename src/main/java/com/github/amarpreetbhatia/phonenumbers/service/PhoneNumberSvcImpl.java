package com.github.amarpreetbhatia.phonenumbers.service;

import com.github.amarpreetbhatia.phonenumbers.entity.Customer;
import com.github.amarpreetbhatia.phonenumbers.entity.PhoneNumber;
import com.github.amarpreetbhatia.phonenumbers.mapper.PhoneNumberMapper;
import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;
import com.github.amarpreetbhatia.phonenumbers.repository.CustomerRepository;
import com.github.amarpreetbhatia.phonenumbers.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PhoneNumberSvcImpl implements PhoneNumberSvc {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberSvcImpl.class.getName());

    private final CustomerRepository customerRepository;
    private final PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberSvcImpl(CustomerRepository customerRepository, PhoneNumberRepository phoneNumberRepository) {
        this.customerRepository = customerRepository;
        this.phoneNumberRepository = phoneNumberRepository;
    }

    @Override
    public List<PhoneNumberVO> getAllPhoneNumbers() {
        LOGGER.info("called getAllPhoneNumbers");
        List<PhoneNumber> phoneNumbers = phoneNumberRepository.findAll();
        if(phoneNumbers == null || phoneNumbers.isEmpty()) {
            LOGGER.info("no phone numbers found");
            return null;
        }
        return PhoneNumberMapper.toPhoneNumberVOList(phoneNumbers);
    }

    @Override
    public List<PhoneNumberVO> getPhoneNumberByCustomerId(Long customerId) {
        LOGGER.info("called getPhoneNumberByCustomerId for customerId {}", customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        List<PhoneNumber> phoneNumbers = phoneNumberRepository.findByCustomer(customer);
        if(phoneNumbers == null || phoneNumbers.isEmpty()) {
            LOGGER.info("no phone numbers found for customer {}", customerId);
            return null;
        }
        return PhoneNumberMapper.toPhoneNumberVOList(phoneNumbers);
    }

    @Override
    @Transactional
    public void activatePhoneNumber(String number) {
        LOGGER.info("called activatePhoneNumber for number {}", number);
        PhoneNumber phoneNumber = phoneNumberRepository.findByNumber(number);
        if(number == null) {
            LOGGER.info("no phone record found to activate the number");
            throw new NoSuchElementException("Phone number not found to activate");
        }
        phoneNumberRepository.updatePhoneNumberActivation(phoneNumber.getId(),true);
    }
}
