package com.example.springboot_sp.service.customer;

import com.example.SpringBootApp.SpringBootSpApplication;
import com.example.customer.CustomerService;
import com.example.customer.dto.CustomerDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest(classes = SpringBootSpApplication.class)
@ActiveProfiles("application")
@Transactional
@Commit
public class CustomerServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);
    private static final Gson GSON = new Gson();
    @Autowired
    private CustomerService testObj;

    @Test
    public void getNewUser() {
        LocalDateTime currentTime = LocalDateTime.now();
        CustomerDto newCustomerDto = new CustomerDto("yushan123","youzhan","test2@", String.valueOf(currentTime));
        newCustomerDto.setObjectId(88);
        String customerUnid = testObj.createNewUser(newCustomerDto);
        LOGGER.warn(customerUnid);
    }

    @Test
    public void getUserByUnid() {
        CustomerDto currentCustomerDto = testObj.doGetCustomer("yushan123");
        LOGGER.info(GSON.toJson(currentCustomerDto));

    }

}