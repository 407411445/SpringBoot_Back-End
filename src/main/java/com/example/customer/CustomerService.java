package com.example.customer;

import com.example.customer.dto.CustomerDto;
import com.example.customer.model.CustomerMapper;
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
            throw new CustomerServiceException(CustomerServiceException.ExceptionType.Undefined,
                    "Create User failed " );
        }
        CustomerInfo entity = CustomerMapper.INSTANCE.dto2Dao(currentUser);

        if (daoHelper.findCustomerByUnId(entity.getUnid())){
            throw new CustomerServiceException(CustomerServiceException.ExceptionType.DuplicateCustomerUnid,
                    "Unique id already exists: " + entity.getUnid());
        }

        InsertResult insertResult= this.daoHelper.insertWithDao(entity);
        return insertResult.getUnid();
    }

    public CustomerDto doGetCustomer(String unid){
        CustomerInfo userEntity = this.customerDao.getCustomerByUnId(unid);
        LOGGER.info("userEntity: " + userEntity);
        // 改成modelmapper寫法 2025/05/05 改用MapStruct
        return CustomerMapper.INSTANCE.dao2Dto(userEntity);
    }

    private static class DaoHelper {
        private final CustomerDao customerDao;

        private DaoHelper(CustomerDao customerDao){

            this.customerDao = customerDao;
        }

        public InsertResult insertWithDao(CustomerInfo entity){
            InsertResult insertResult = this.customerDao.insertNewCustomerInfo(entity);

            return insertResult;
        }

        public boolean findCustomerByUnId(String unid){
            CustomerInfo userEntity = this.customerDao.getCustomerByUnId(unid);

            return userEntity!=null;
        }
    }
}
