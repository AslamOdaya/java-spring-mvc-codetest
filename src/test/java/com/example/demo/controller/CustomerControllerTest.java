package com.example.demo.controller;


import com.example.demo.model.Customer;
import com.example.demo.service.CustomerServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {CustomerController.class, CustomerServiceImpl.class})
@WebMvcTest
public class CustomerControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSortByDueDate_customersSortedByDueDate() throws Exception {
        String expectedJson = "[{\"id\":10000001,\"name\":\"Bruce Cardenas\"," + "\"duetime\":\"2013-12-28T22:11:12Z\",\"jointime\":\"2014-07-04T04:53:42Z\"}," +
                "{\"id\":10000002,\"name\":\"Barrett Peterson\",\"duetime\":\"2013-12-30T06:33:23Z\"," + "\"jointime\":\"2014-10-24T04:46:24Z\"}," +
                "{\"id\":10000000,\"name\":" + "\"Ulysses Leon\",\"duetime\":\"2014-06-18T13:26:56Z\",\"jointime\":\"2015-04-08T19:47:16Z\"}]";

        JSONObject customer = new JSONObject();
        customer.put("jointime", ZonedDateTime.parse("2015-04-08T12:47:16-07:00").toString());
        customer.put("id", 10000000);
        customer.put("name", "Ulysses Leon");
        customer.put("duetime", ZonedDateTime.parse("2014-06-18T06:26:56-07:00").toString());

        JSONObject customer1 = new JSONObject();
        customer1.put("id", 10000001);
        customer1.put("name", "Bruce Cardenas");
        customer1.put("duetime", ZonedDateTime.parse("2013-12-28T14:11:12-08:00").toString());
        customer1.put("jointime", ZonedDateTime.parse("2014-07-03T21:53:42-07:00").toString());

        JSONObject customer2 = new JSONObject();
        customer2.put("id", 10000002);
        customer2.put("name", "Barrett Peterson");
        customer2.put("duetime", ZonedDateTime.parse("2013-12-29T22:33:23-08:00").toString());
        customer2.put("jointime", ZonedDateTime.parse("2014-10-23T21:46:24-07:00").toString());

        JSONArray customersArr = new JSONArray();
        customersArr.put(customer);
        customersArr.put(customer1);
        customersArr.put(customer2);

        String jsonString = customersArr.toString();

        MvcResult result = mockMvc.perform((MockMvcRequestBuilders.post("/customers/sorted")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertEquals(expectedJson, response);
    }


    @Test
    public void testSortByDueDate_sortLargeDataSet() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        File customersFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("customers.json")).getFile());
        File sortedCustomersFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("sortedCustomers.json")).getFile());

        List<Customer> customers = mapper.readValue(customersFile, new TypeReference<List<Customer>>() {
        });

        List<Customer> sortedCustomers = mapper.readValue(sortedCustomersFile, new TypeReference<List<Customer>>() {
        });

        String sortedCustomerArr = mapper.writeValueAsString(sortedCustomers);

        String customersString = mapper.writeValueAsString(customers);

        MvcResult result = mockMvc.perform((MockMvcRequestBuilders.post("/customers/sorted")
                .content(customersString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)))
                .andExpect(status().isCreated())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals(sortedCustomerArr, response);
    }

}
