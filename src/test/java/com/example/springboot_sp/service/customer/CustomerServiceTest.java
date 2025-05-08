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
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(classes = SpringBootSpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {"server.port=8080"})
public class CustomerServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceTest.class);
    private static final Gson GSON = new Gson();
    @Autowired
    private CustomerService testObj;

    @Test
    public void getNewUser() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = LocalDateTime.now().format(formatter);

        CustomerDto newCustomerDto = new CustomerDto("yushan123","youzhan","test2@", formattedTime);
        String customerUnid = testObj.createNewUser(newCustomerDto);
        try {
            Thread.sleep(50000);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        LOGGER.warn(customerUnid);
    }

    @Test
    public void getUserByUnid() {
        CustomerDto currentCustomerDto = testObj.doGetCustomer("22666666");
        LOGGER.info(GSON.toJson(currentCustomerDto));

    }

}