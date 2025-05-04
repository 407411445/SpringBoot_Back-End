package com.example.springboot_sp.service.blog;

import com.example.SpringBootApp.SpringBootSpApplication;
import com.example.blog.BlogService;
import com.example.blog.dto.BlogDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest(classes = SpringBootSpApplication.class)

@Transactional

public class BlogServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogServiceTest.class);
    private static final Gson GSON = new Gson();
    @Autowired
    private BlogService testObj;

    @Test
    public void getNewBlog() {
        LocalDateTime currentTime = LocalDateTime.now();
        BlogDto newBlogDto = new BlogDto("yushan123",
                "youzhan",
                "testContent",
                currentTime.toString(),
                currentTime.toString());
        newBlogDto.setObjectId(88);
        newBlogDto.setHeadline("Gura畢業快樂");
        String blogUnid = testObj.createNewBlog(newBlogDto);
        LOGGER.warn(blogUnid);
    }

//    @Test
//    public void getUserByUnid() {
//        BlogDto currentCustomerDto = testObj.("yushan123");
//        LOGGER.info(GSON.toJson(currentCustomerDto));
//
//    }

}