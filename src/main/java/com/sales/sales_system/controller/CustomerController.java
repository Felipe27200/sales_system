package com.sales.sales_system.controller;

import com.sales.sales_system.entity.Customer;
import com.sales.sales_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController
{
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> findAll()
    {
        return this.customerService.findAll();
    }
}
