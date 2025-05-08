package com.example.controller.entity;

import com.example.blog.BlogService;
import com.example.blog.dto.BlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/entity/BlogAPI"})
public class BlogCtrl {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = {""}, method = {RequestMethod.PUT})
    public ResponseEntity<?> insertNewBlog(@RequestBody BlogDto blogDto) throws Exception {

        String blogUnid = this.blogService.createNewBlog(blogDto);
        return new ResponseEntity<CreateResult>(new CreateResult(blogUnid),
                HttpStatus.OK);
    }

    @RequestMapping(value = {"/{blogUnid}"}, method = {RequestMethod.GET})
    public ResponseEntity<?> getUser(@PathVariable String blogUnid) throws Exception {
        BlogDto blogDto= this.blogService.doGetBlog(blogUnid);
        if (blogDto != null) {
            return new ResponseEntity<>(blogDto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("blog not found!!!!",HttpStatus.BAD_REQUEST);
        }
    }



    private static class CreateResult{
        private final String blogUnid;
        public CreateResult(String blogUnid){
            this.blogUnid = blogUnid;
        }

        public String getBlogUnid(){
            return blogUnid;
        }

    }
}
