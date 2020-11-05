package com.example.demo.controller;


import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController()
@RequestMapping("customers/")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("sorted")
    public ResponseEntity<List<Customer>> sortCustomersByDueDate(@RequestBody List<Customer> customersToSort) {

        List<Customer> sortedCustomers = customerService.sortByDueTime(customersToSort);

        return new ResponseEntity<>(sortedCustomers, HttpStatus.CREATED);

    }
}
