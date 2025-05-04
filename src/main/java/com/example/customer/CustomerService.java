package com.example.customer;

import com.example.customer.dto.CustomerDto;
import com.example.customer.model.dao.CustomerDao;
import com.example.customer.model.rdb.CustomerInfo;

import com.example.customer.model.dao.CustomerDao.InsertResult;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service("customerService")
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerDao customerDao;

    private DaoHelper daoHelper;

    @PostConstruct
    public void postInit(){
        this.daoHelper =new DaoHelper(this.customerDao);
    }

    @Transactional
    public String createNewUser(CustomerDto currentUser){
        if(LOGGER.isDebugEnabled()){
            Gson gson = new Gson();
            LOGGER.warn("inputDto: " + gson.toJson(currentUser));
        }

        CustomerDto creator = currentUser;
        if(creator ==null){
            throw new RuntimeException("Create user failed: " +currentUser.getName() );
        }
        LocalDateTime createLocalTime = LocalDateTime.now();
        CustomerInfo entity = new CustomerInfo();
        entity.setName(creator.getName());
        entity.setEmail(creator.getEmail());
        entity.setCreateTime(createLocalTime);
        entity.setUnid(creator.getUnid());

        InsertResult insertResult= this.daoHelper.insertWithDao(entity);
        try {
            return insertResult.getUnid();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CustomerDto doGetCustomer(String unid){
        CustomerInfo userEntity = this.customerDao.getCustomerByUnId(unid);
        LOGGER.info("userEntity: " + userEntity);
        LocalDateTime userEntityCreateTime = userEntity.getCreateTime();
        //TODO 改成modelmapper寫法
        return new CustomerDto(userEntity.getUnid(),userEntity.getName(),userEntity.getEmail(),String.valueOf(userEntityCreateTime));
    }

    private static class DaoHelper {
        private final CustomerDao customerDao;

        private DaoHelper(CustomerDao customerDao){

            this.customerDao = customerDao;
        }

        public InsertResult insertWithDao(CustomerInfo entity){
            InsertResult insertResult = this.customerDao.insertNewCustomerInfo(entity);

            this.customerDao.update(entity);
            return insertResult;
        }
    }
}
