package com.example.customer.model.dao;

import com.daosupport.DaoBase;
import com.example.customer.model.rdb.CustomerInfo;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository

public class CustomerDao extends DaoBase<CustomerInfo, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDao.class);
    private static final CustomerInfo entityType = new CustomerInfo();



    public CustomerDao() {
        if(LOGGER.isDebugEnabled()){
            LOGGER.warn("CustomerDao Constructor");
        }
    }

    @Override
    protected Class<CustomerInfo> getRealEntityClass() {
     if(LOGGER.isDebugEnabled()){
         LOGGER.debug("getRealEntityClass");
     }
     @SuppressWarnings("unchecked")
     Class<CustomerInfo> class1 = (Class<CustomerInfo>) entityType.getClass();
        return class1;
    }

    public CustomerInfo getCustomerByUnId(String unid)  {
        String hql = "from CustomerInfo ci where ci.unid=:unid";
        @SuppressWarnings("unchecked")
        Query<CustomerInfo> query =this.getCurrentSession().createQuery(hql);
        query.setParameter("unid", unid);
        return query.uniqueResult();
    }

    public InsertResult insertNewCustomerInfo(CustomerInfo entity){
        this.insert(entity);
        Integer objectId = entity.getObjectId();
        String unid = entity.getUnid();
        return new InsertResult(objectId,unid);
    }

    @Override
    public List<CustomerInfo> findAll() {
        return super.findAll();
    }

    public static class InsertResult {
        private final Integer objectid;
        private final String unid;
        public InsertResult(Integer objectid, String unid) {
            super();
            this.objectid = objectid;
            this.unid = unid;
        }
        public Integer getObjectid() {
            return objectid;
        }
        public String getUnid() {
            return unid;
        }
    }

}
