package com.sales.sales_system.service;

import com.sales.sales_system.entity.Customer;
import com.sales.sales_system.entity.Role;
import com.sales.sales_system.error_handling.db_errors.DuplicateConstraintException;
import com.sales.sales_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService
{
    private CustomerRepository customerRespository;
    private RoleService roleService;

    @Autowired
    public CustomerService(
        CustomerRepository customerRepository,
        RoleService roleService
    )
    {
        this.customerRespository = customerRepository;
        this.roleService = roleService;
    }

    public Customer save(Customer newCustomer)
    {
        this.checkUniqueDocument(newCustomer.getDocument());

        Role role = roleService.findById(newCustomer.getRole().getId());
        newCustomer.setRole(role);

        return this.customerRespository.save(newCustomer);
    }

    public List<Customer> findAll()
    {
        return this.customerRespository.findAll();
    }

    private void checkUniqueDocument(Long document)
    {
        Customer customer = this.customerRespository.findCustomerByDocument(document);

        if (customer != null)
            throw new DuplicateConstraintException("The document '" + document +"' already registered.");
    }

    private void checkUniqueDocument(Long document, Long customerDocument)
    {
        Customer customer = this.customerRespository.findCustomerByDocument(document);

        if (customer != null && !customer.getDocument().equals(customerDocument))
            throw new DuplicateConstraintException("The document '" + document +"' already registered.");
    }

    private void checkUniqueUser(String user)
    {
        Customer customer = this.customerRespository.findCustomerByUser(user);

        if (customer != null)
            throw new DuplicateConstraintException("The user '" + user +"' already registered.");
    }

    private void checkUniqueUser(String user, Long id)
    {
        Customer customer = this.customerRespository.findCustomerByUser(user);

        if (customer != null && !customer.getId().equals(id))
            throw new DuplicateConstraintException("The user '" + user +"' already registered.");
    }
}
