package com.sales.sales_system.service;

import com.sales.sales_system.entity.Customer;
import com.sales.sales_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    private CustomerRepository customerRespository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRespository = customerRepository;
    }

    public List<Customer> findAll()
    {
        return this.customerRespository.findAll();
    }
}
