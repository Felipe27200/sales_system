package com.sales.sales_system.controller;

import com.sales.sales_system.dto.user.UserGetDTO;
import com.sales.sales_system.dto.user.UserStoreDTO;
import com.sales.sales_system.entity.Customer;
import com.sales.sales_system.entity.Role;
import com.sales.sales_system.response.Response;
import com.sales.sales_system.service.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "${apiPrefix}")
public class CustomerController
{
    private CustomerService customerService;
    private ModelMapper modelMapper;

    @Autowired
    public CustomerController(
        CustomerService customerService,
        ModelMapper modelMapper
    )
    {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/customers")
    public Response<?> saveCustomer(@Valid @RequestBody UserStoreDTO userStoreDTO)
    {
        Customer customer = modelMapper.map(userStoreDTO, Customer.class);
        customer.setRole(new Role(userStoreDTO.getRole_id()));

        if (!customer.getPassword().equals(userStoreDTO.getConfirmPassword()))
            return new Response<>("The confirm password doesn't ", "successful");

        Customer newCustomer = this.customerService.save(customer);
        UserGetDTO userGetDTO = modelMapper.map(newCustomer, UserGetDTO.class);

        return new Response<>(userGetDTO, "successful");
    }

    @GetMapping("/customers")
    public List<Customer> findAll()
    {
        List<Customer> usersList = this.customerService.findAll();

        if (usersList.isEmpty())
            new Response<>(usersList, "successful");

        m

        return this.customerService.findAll();
    }
}
