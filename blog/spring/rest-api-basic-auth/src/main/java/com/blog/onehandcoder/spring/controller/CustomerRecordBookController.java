package com.blog.onehandcoder.spring.controller;

import com.blog.onehandcoder.spring.CustomerRecordBook;
import com.blog.onehandcoder.spring.model.CustomerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerRecordBookController {

    @Autowired
    private CustomerRecordBook customerRecordBook;

    @GetMapping("/customer/records")
    public List<CustomerRecord> getCustomerRecords() {
        return customerRecordBook.getCustomerRecords();
    }

    @GetMapping("/customer/record/{customer}")
    public CustomerRecord getCustomerRecord(@PathVariable("customer") String customer) {
        for (CustomerRecord customerRecord : customerRecordBook.getCustomerRecords()) {
            if (customerRecord.getName().equals(customer)) {
                return customerRecord;
            }
        };
        return null;
    }

    @PostMapping("/customer/record")
    public void addCustomerRecord(@RequestBody CustomerRecord customerRecord) {
        customerRecordBook.getCustomerRecords().add(customerRecord);
    }

}
