package com.example.blog;

import com.example.blog.dto.BlogDto;
import com.example.blog.model.BlogMapper;
import com.example.blog.model.dao.BlogDao;
import com.example.blog.model.dao.BlogDao.InsertResult;
import com.example.blog.model.rdb.BlogInfo;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new BlogServiceException(BlogServiceException.ExceptionType.Undefined,
                    "Blog object is null");
        }
        blog.setUpdateTime(blog.getCreateTime());
        BlogInfo entity = BlogMapper.INSTANCE.dto2Dao(blog);

        if(daoHelper.findBlogByUnid(entity.getUnid())){
            //這裡怪怪的 已經用日期去給Unid 不會有重複的問題 以後改
            throw new BlogServiceException(BlogServiceException.ExceptionType.DuplicateBlogUnid,
                    " blog unid already exists");
        }
        BlogDao.InsertResult insertResult= this.daoHelper.insertWithDao(entity);

        return insertResult.getUnid();

    }

    public BlogDto doGetBlog(String unid){
        BlogInfo blogEntity = this.blogDao.getBlogByUnId(unid);
        LOGGER.info("blogEntity: " + blogEntity);
        //2025/05/08 改成MapStruct
        return BlogMapper.INSTANCE.dao2Dto(blogEntity);
    }

    private static class DaoHelper {
        private final BlogDao BlogDao;

        private DaoHelper(BlogDao BlogDao){

            this.BlogDao = BlogDao;
        }

        public InsertResult insertWithDao(BlogInfo entity){
            InsertResult insertResult = this.BlogDao.insertNewBlogInfo(entity);
            return insertResult;
        }

        public boolean findBlogByUnid(String unid){
            BlogInfo blogEntity = this.BlogDao.getBlogByUnId(unid);

            return blogEntity!=null;
        }
    }
}
