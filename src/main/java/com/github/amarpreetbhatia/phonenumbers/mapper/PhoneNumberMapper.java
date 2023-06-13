package com.github.amarpreetbhatia.phonenumbers.mapper;


import com.github.amarpreetbhatia.phonenumbers.entity.PhoneNumber;
import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;
import com.github.amarpreetbhatia.phonenumbers.service.PhoneNumberSvcImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PhoneNumberMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberSvcImpl.class.getName());

    public static PhoneNumberVO toPhoneNumberVO(PhoneNumber phoneNumber) {
        LOGGER.info("PhoneNumberMapper.toPhoneNumberVO called");
        PhoneNumberVO phoneNumberVO = new PhoneNumberVO();
        phoneNumberVO.setId(phoneNumber.getId());
        phoneNumberVO.setNumber(phoneNumber.getNumber());
        phoneNumberVO.setActive(phoneNumber.getActive());
        if(phoneNumber.getCustomer() != null) {
            phoneNumberVO.setCustomerId(phoneNumber.getCustomer().getId());
        }
        return phoneNumberVO;
    }

    public static List<PhoneNumberVO> toPhoneNumberVOList(List<PhoneNumber> phoneNumbers) {
        LOGGER.info("PhoneNumberMapper.toPhoneNumberVOList called");
        return phoneNumbers.stream()
                .map(PhoneNumberMapper::toPhoneNumberVO)
                .collect(Collectors.toList());
    }
}
