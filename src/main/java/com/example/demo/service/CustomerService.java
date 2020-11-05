package com.example.demo.service;


import com.example.demo.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    List<Customer> sortByDueTime(List<Customer> customers);
}
