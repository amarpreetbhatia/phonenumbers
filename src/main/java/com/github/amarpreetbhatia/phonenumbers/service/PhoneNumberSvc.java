package com.github.amarpreetbhatia.phonenumbers.service;

import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;

import java.util.List;

public interface PhoneNumberSvc {
    List<PhoneNumberVO> getAllPhoneNumbers();
    List<PhoneNumberVO> getPhoneNumberByCustomerId(Long customerId);
    void activatePhoneNumber(String PhoneNumber);

}
