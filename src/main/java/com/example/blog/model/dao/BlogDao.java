package com.example.blog.model.dao;

import com.daosupport.DaoBase;
import com.example.blog.model.rdb.BlogInfo;
import com.example.utils.UnidResultUtils;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class BlogDao extends DaoBase<BlogInfo, Integer> {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.example.customer.model.dao.CustomerDao.class);
    private static final BlogInfo entityType = new BlogInfo();

    @Autowired
    private UnidResultUtils unidResultUtils;

    public BlogDao() {
        if(LOGGER.isDebugEnabled()){
            LOGGER.warn("CustomerDao Constructor");
        }
    }

    @Override
    protected Class<BlogInfo> getRealEntityClass() {
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("getRealEntityClass");
        }
        @SuppressWarnings("unchecked")
        Class<BlogInfo> class1 = (Class<BlogInfo>) entityType.getClass();
        return class1;
    }

    public BlogInfo getBlogByUnId(String unid)  {
        String hql = "from BlogInfo bi where bi.unid=:unid";
        @SuppressWarnings("unchecked")
        Query<BlogInfo> query =this.getCurrentSession().createQuery(hql);
        query.setParameter("unid", unid);
        return query.uniqueResult();
    }

    public InsertResult insertNewBlogInfo(BlogInfo entity){
        String twDateStr = this.getTwDateStr(new Date());
        System.out.println("----------" + twDateStr);
        String blogUnid = this.unidResultUtils.getUnid("B",twDateStr) ;
        entity.setUnid(blogUnid);
        this.insert(entity);
        Integer objectId = entity.getObjectId();
        String unid = entity.getUnid();
        return new InsertResult(objectId,unid);
    }
    private String getTwDateStr(Date date) {
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
        int zhYear = Integer.parseInt(dateStr.substring(0, 4))- 1911;
        return String.format("%03d%s" , zhYear, dateStr.substring(4));
    }

    @Override
    public List<BlogInfo> findAll() {
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
