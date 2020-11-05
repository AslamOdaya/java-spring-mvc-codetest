package com.example.demo.service;

import com.example.demo.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CustomerServiceTest {


    private CustomerService customerService;
    private ConcurrentMap<Long, Customer> customers;

    @BeforeEach
    public void setup(){
        customers = new ConcurrentHashMap<>();
        customerService = new CustomerServiceImpl();
    }

    @Test
    public void testSortByDueTime_forGivenCustomers_CustomersIsNonEmpty(){
        List<Customer> customersToSort = new ArrayList<>();
        customersToSort.add(new Customer(10000000,"Ulysses Leon", ZonedDateTime.parse("2014-06-18T06:26:56-07:00")
                , ZonedDateTime.parse("2015-04-08T12:47:16-07:00")));
        List<Customer> actualList = customerService.sortByDueTime(customersToSort);
        assertFalse(actualList.isEmpty());
    }

    @Test
    public void testSortByDueItem_forGivenCustomers_sortedByDueTime(){
        List<Customer> customersToSort = new ArrayList<>();
        customersToSort.add(new Customer(10000000,"Ulysses Leon", ZonedDateTime.parse("2014-06-18T06:26:56-07:00")
                , ZonedDateTime.parse("2015-04-08T12:47:16-07:00")));
        customersToSort.add(new Customer(10000001,"Bruce Cardenas",ZonedDateTime.parse("2013-12-28T14:11:12-08:00")
                , ZonedDateTime.parse("2014-07-03T21:53:42-07:00")));

        List<Customer> actualList = customerService.sortByDueTime(customersToSort);

        assertNotEquals(customersToSort,actualList);
    }
}

