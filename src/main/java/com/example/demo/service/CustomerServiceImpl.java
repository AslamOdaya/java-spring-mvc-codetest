package com.example.demo.service;

import com.example.demo.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

@Service
public class CustomerServiceImpl implements CustomerService {



    @Override
    public List<Customer> sortByDueTime(List<Customer> customersToSort) {

        List<Customer> sortedCustomers = new ArrayList<>(customersToSort);

        Collections.sort(sortedCustomers);

        return sortedCustomers;
    }
}
