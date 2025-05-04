package com.example.springboot_sp.service.blog;

import com.example.SpringBootApp.SpringBootSpApplication;
import com.example.blog.model.dao.BlogDao;
import com.example.blog.model.dao.BlogDao.InsertResult;
import com.example.blog.model.rdb.BlogInfo;
import com.example.springboot_sp.service.customer.CustomerDaoTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;
@SpringBootTest(classes = SpringBootSpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = {"server.port=8080"})
public class BlogDaoTest {

    @Autowired
    private BlogDao testObj;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerDaoTest.class);

    @Test
    void Test() {
        String unid = "test";
        String headLine = "標題";
        String author = "哭啊作者";
        String content = "不知道寫什麼";

        LocalDateTime createTime = LocalDateTime.now();

        BlogInfo entity = new BlogInfo();

        entity.setUnid(unid);
        entity.setHeadline(headLine);
        entity.setAuthor(author);
        entity.setContent(content);
        entity.setCreateTime(createTime);
        entity.setUpdateTime(createTime);

        try {
            InsertResult result =this.testObj.insertNewBlogInfo(entity);
            Thread.sleep(30000);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        List<BlogInfo> list = this.testObj.findAll();
        for (BlogInfo blogInfo : list) {
            LOGGER.info("BlogInfoObjId:{}",blogInfo.getObjectId());
        }
    }

    @Test
    void findAll() {
        List<BlogInfo> list = this.testObj.findAll();
        for (BlogInfo blogInfo : list) {
            LOGGER.info("BlogInfoUnid:{}",blogInfo.getUnid());
        }
    }
}
