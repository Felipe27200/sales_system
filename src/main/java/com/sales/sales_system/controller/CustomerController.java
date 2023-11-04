package com.sales.sales_system.controller;

import com.sales.sales_system.dto.user.UserGetDTO;
import com.sales.sales_system.dto.user.UserStoreDTO;
import com.sales.sales_system.dto.user.UserUpdateDTO;
import com.sales.sales_system.entity.Customer;
import com.sales.sales_system.entity.Role;
import com.sales.sales_system.response.Response;
import com.sales.sales_system.service.CustomerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
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
            return new Response<>("The confirm password doesn't match", "successful");

        Customer newCustomer = this.customerService.save(customer);
        UserGetDTO userGetDTO = modelMapper.map(newCustomer, UserGetDTO.class);

        return new Response<>(userGetDTO, "successful");
    }

    @PutMapping("/customers/{id}")
    public Response<?> updateCustomer(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id)
    {
        if (!userUpdateDTO.getPassword().equals(userUpdateDTO.getConfirmPassword()))
            return new Response<>("The confirm password doesn't match", "successful");

        Customer customer = modelMapper.map(userUpdateDTO, Customer.class);
        customer.setRole(new Role(userUpdateDTO.getRole_id()));

        Customer updateCustomer = this.customerService.updateCustomer(customer, id);
        UserGetDTO userGetDTO = modelMapper.map(updateCustomer, UserGetDTO.class);

        return new Response<>(userGetDTO, "successful");
    }

    @GetMapping("/customers")
    public Response<?> findAll()
    {
        List<Customer> usersList = this.customerService.findAll();

        if (usersList.isEmpty())
            new Response<>(usersList, "successful");

        List<UserGetDTO> userDtoList = UserGetDTO.convertListEntityToListDTO(usersList);

        return new Response<>(userDtoList, "successfull");
    }

    @GetMapping("/customers/{id}")
    public Response<?> findAll(@PathVariable Long id)
    {
        Customer customer = this.customerService.findById(id);
        UserGetDTO userGetDTO = modelMapper.map(customer, UserGetDTO.class);

        return new Response<>(userGetDTO, "successfull");
    }

    @DeleteMapping("/customers/{id}")
    public Response<?> deleteByid(@PathVariable Long id)
    {
        Customer customer = this.customerService.deleteById(id);

        return new Response<>("The user '"
            + customer.getName()
            + "' "
            + "with document: '" + customer.getDocument() + "'"
            + " was deleted.",
            "successfull");
    }
}
