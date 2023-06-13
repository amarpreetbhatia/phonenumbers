package com.github.amarpreetbhatia.phonenumbers.controller;

import com.github.amarpreetbhatia.phonenumbers.model.PhoneNumberVO;
import com.github.amarpreetbhatia.phonenumbers.service.PhoneNumberSvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



class PhoneNumberControllerTest {
    private static Logger LOGGER = LoggerFactory.getLogger(PhoneNumberControllerTest.class.getName());

    private MockMvc mockMvc;

    @Mock
    private PhoneNumberSvc phoneNumberService;

    @InjectMocks
    private PhoneNumberController phoneNumberController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phoneNumberController).build();
    }

    @Test
    public void testGetAllPhoneNumbers() throws Exception {
        List<PhoneNumberVO> phoneNumbers = getDummyPhoneNumberVOS();

        when(phoneNumberService.getAllPhoneNumbers()).thenReturn(phoneNumbers);

        MvcResult result = mockMvc.perform(get("/api/phoneNumbers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(phoneNumbers.size()))
                .andExpect(jsonPath("$[0].id").value(phoneNumbers.get(0).getId()))
                .andExpect(jsonPath("$[0].mobileNumber").value(phoneNumbers.get(0).getNumber()))
                .andExpect(jsonPath("$[0].customerId").value(phoneNumbers.get(0).getCustomerId()))
                .andExpect(jsonPath("$[0].active").value(phoneNumbers.get(0).isActive()))
                .andExpect(jsonPath("$[1].id").value(phoneNumbers.get(1).getId()))
                .andExpect(jsonPath("$[1].mobileNumber").value(phoneNumbers.get(1).getNumber()))
                .andExpect(jsonPath("$[1].customerId").value(phoneNumbers.get(1).getCustomerId()))
                .andExpect(jsonPath("$[1].active").value(phoneNumbers.get(1).isActive()))
                .andReturn();
        String responseContent = result.getResponse().getContentAsString();
        LOGGER.info("Response: {}", responseContent);


        verify(phoneNumberService, times(1)).getAllPhoneNumbers();
        verifyNoMoreInteractions(phoneNumberService);
    }

    @Test
    public void testGetPhoneNumbersForCustomer() throws Exception {
        List<PhoneNumberVO> phoneNumbers = getDummyPhoneNumberVOS();
        when(phoneNumberService.getPhoneNumberByCustomerId(1L)).thenReturn(phoneNumbers);

        MvcResult result = mockMvc.perform(get("/api/phoneNumbers/customer/{customerId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(phoneNumbers.size()))
                .andExpect(jsonPath("$[0].id").value(phoneNumbers.get(0).getId()))
                .andExpect(jsonPath("$[0].mobileNumber").value(phoneNumbers.get(0).getNumber()))
                .andExpect(jsonPath("$[0].active").value(phoneNumbers.get(0).isActive()))
                .andExpect(jsonPath("$[0].customerId").value(phoneNumbers.get(0).getCustomerId()))
                .andExpect(jsonPath("$[1].id").value(phoneNumbers.get(1).getId()))
                .andExpect(jsonPath("$[1].mobileNumber").value(phoneNumbers.get(1).getNumber()))
                .andExpect(jsonPath("$[1].active").value(phoneNumbers.get(1).isActive()))
                .andExpect(jsonPath("$[1].customerId").value(phoneNumbers.get(1).getCustomerId()))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseContent);

        verify(phoneNumberService, times(1)).getPhoneNumberByCustomerId(1L);
        verifyNoMoreInteractions(phoneNumberService);
    }

    @Test
    public void testActivatePhoneNumber() throws Exception {
        String phoneNumber = "061444444444";

        doNothing().when(phoneNumberService).activatePhoneNumber(phoneNumber);

        MvcResult result = mockMvc.perform(patch("/api/phoneNumbers/{phoneNumberId}/activate", phoneNumber))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        System.out.println("Response: " + responseContent);

        verify(phoneNumberService, times(1)).activatePhoneNumber(phoneNumber);
        verifyNoMoreInteractions(phoneNumberService);
    }


    private List<PhoneNumberVO> getDummyPhoneNumberVOS() {
        PhoneNumberVO phoneNumberVO1 = new PhoneNumberVO();
        phoneNumberVO1.setId(1L);
        phoneNumberVO1.setNumber("1234567890");
        phoneNumberVO1.setActive(true);
        phoneNumberVO1.setCustomerId(1L);

        PhoneNumberVO phoneNumberVO2 = new PhoneNumberVO();
        phoneNumberVO2.setId(2L);
        phoneNumberVO2.setNumber("9876543210");
        phoneNumberVO2.setActive(false);
        phoneNumberVO2.setCustomerId(2L);
        List<PhoneNumberVO> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(phoneNumberVO1);
        phoneNumbers.add(phoneNumberVO2);
        return phoneNumbers;
    }
}