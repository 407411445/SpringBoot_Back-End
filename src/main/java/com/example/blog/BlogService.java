package com.example.blog;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.dao.BlogDao;
import com.example.blog.model.rdb.BlogInfo;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import com.example.blog.model.dao.BlogDao.InsertResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("blogService")
public class BlogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.example.customer.CustomerService.class);

    @Autowired
    private BlogDao blogDao;

    private DaoHelper daoHelper;

    @PostConstruct
    public void postInit(){
        this.daoHelper =new DaoHelper(this.blogDao);
    }


    public String createNewBlog(BlogDto blog){
        if(LOGGER.isDebugEnabled()){
            Gson gson = new Gson();
            LOGGER.warn("inputDto: " + gson.toJson(blog));
        }

        BlogDto newBloginfo = blog;
        if(newBloginfo ==null){
            throw new RuntimeException("Create blog failed: " +newBloginfo.getHeadline() );
        }
        LocalDateTime createLocalTime = LocalDateTime.now();
        BlogInfo entity = new BlogInfo();
        entity.setUnid(newBloginfo.getUnid());
        entity.setHeadline(newBloginfo.getHeadline());
        entity.setContent(newBloginfo.getContent());
        entity.setAuthor(newBloginfo.getAuthor());
        entity.setCreateTime(createLocalTime);
        entity.setUpdateTime(createLocalTime);

        BlogDao.InsertResult insertResult= this.daoHelper.insertWithDao(entity);
        try {
            return insertResult.getUnid();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public BlogDto doGetBlog(String unid){
        BlogInfo blogEntity = this.blogDao.getBlogByUnId(unid);
        LOGGER.info("userEntity: " + blogEntity);
        LocalDateTime blogCreateTime = blogEntity.getCreateTime();
        //TODO 改成modelmapper寫法
        return new BlogDto(blogEntity.getUnid(),blogEntity.getAuthor(),blogEntity.getContent(),String.valueOf(blogCreateTime),String.valueOf(blogCreateTime));
    }

    private static class DaoHelper {
        private final BlogDao BlogDao;

        private DaoHelper(BlogDao BlogDao){

            this.BlogDao = BlogDao;
        }

        public InsertResult insertWithDao(BlogInfo entity){
            InsertResult insertResult = this.BlogDao.insertNewBlogInfo(entity);
            this.BlogDao.update(entity);
            return insertResult;
        }
    }
}
