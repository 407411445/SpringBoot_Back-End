package com.example.springboot_sp.service.customer;

import com.example.customer.model.dao.CustomerDao;
import com.example.customer.model.dao.CustomerDao.InsertResult;
import com.example.customer.model.rdb.CustomerInfo;
import com.example.SpringBootApp.SpringBootSpApplication;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(classes = SpringBootSpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {"server.port=8080"})
public class CustomerDaoTest {

    @Autowired
    private CustomerDao testObj;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoTest.class);

    @Test
    void Test() {
        String unid = "casuser";
        String name = "哭啊";
        String email = "asd5893852@gmail.com";
        LocalDateTime createTime = LocalDateTime.now();

        CustomerInfo entity = new CustomerInfo();

        entity.setEmail(email);
        entity.setName(name);
        entity.setUnid(unid);
        entity.setCreateTime(createTime);
        InsertResult result =this.testObj.insertNewCustomerInfo(entity);
        this.testObj.flushAndClear();
        LOGGER.info(result.getObjectid()+ "++++++++++++++++++" + result.getUnid());

        try {
            Thread.sleep(50000);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        List<CustomerInfo> list = this.testObj.findAll();
        for (CustomerInfo customerInfo : list) {
            LOGGER.info("customerInfoObjId:{},{}",customerInfo.getObjectId(),customerInfo.getUnid());
        }
    }

    @Test
    void findAll() {
        List<CustomerInfo> list = this.testObj.findAll();
        for (CustomerInfo customerInfo : list) {
            LOGGER.info("customerInfoUnid:{}",customerInfo.getUnid());
        }
    }
}
