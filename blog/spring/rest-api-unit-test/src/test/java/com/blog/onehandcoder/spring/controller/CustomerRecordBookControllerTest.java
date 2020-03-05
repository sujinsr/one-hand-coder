package com.blog.onehandcoder.spring.controller;

import com.blog.onehandcoder.spring.model.CustomerRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerRecordBookController.class)
public class CustomerRecordBookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CustomerRecordBookController customerRecordBookController;

    private JacksonTester<List<CustomerRecord>> jsonSuperHero;

    @Before
    public void before() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getCustomerRecords() throws Exception {
        List<CustomerRecord> customerRecords = new ArrayList<>();
        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setName("Bob");
        customerRecord.setAge(29);
        customerRecord.setEmail("bob@gmail.com");
        customerRecords.add(customerRecord);

        given(customerRecordBookController.getCustomerRecords()).willReturn(customerRecords);

        MockHttpServletResponse response = mvc.perform(get("/customer/records").contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();
        System.out.println(response.getStatus());
        System.out.println(HttpStatus.OK);
        System.out.println(HttpStatus.OK.value());

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(response.getContentAsString(), jsonSuperHero.write(customerRecords).getJson() );
          /*      .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));*/


    }
}
