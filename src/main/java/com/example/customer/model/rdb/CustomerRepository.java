package com.example.customer.model.rdb;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerInfo, String> {

    CustomerInfo findCustomerByName(String name);
}
