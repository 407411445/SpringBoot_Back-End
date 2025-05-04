package com.example.controller.entity;

import com.example.customer.CustomerService;
import com.example.customer.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/entity/UserAPI"})
public class CustomerCtrl {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = {""}, method = {RequestMethod.PUT})
    public ResponseEntity<?> insertNewUser(@RequestBody CustomerDto customerDto) throws Exception {

        String userUnid = this.customerService.createNewUser(customerDto);
        return new ResponseEntity<CreateResult>(new CreateResult(userUnid),
                    HttpStatus.OK);
    }

    @RequestMapping(value = {"/{userUnid}"}, method = {RequestMethod.GET})
    public ResponseEntity<?> getUser(@PathVariable String userUnid) throws Exception {
        CustomerDto customerDto= this.customerService.doGetCustomer(userUnid);
        if (customerDto != null) {
            return new ResponseEntity<CustomerDto>(customerDto,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<String>("user not found!!!!",HttpStatus.BAD_REQUEST);
        }
    }



    private static class CreateResult{
        private final String userUnid;
        public CreateResult(String userUnid){
            this.userUnid = userUnid;
        }

        public String getUserUnid(){
            return userUnid;
        }

    }
}
