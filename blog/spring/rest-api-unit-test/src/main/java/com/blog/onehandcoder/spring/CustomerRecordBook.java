package com.blog.onehandcoder.spring;

import com.blog.onehandcoder.spring.model.CustomerRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRecordBook {

    private List<CustomerRecord> customerRecords = new ArrayList<>();

    public List<CustomerRecord> getCustomerRecords() {
        return customerRecords;
    }
}
