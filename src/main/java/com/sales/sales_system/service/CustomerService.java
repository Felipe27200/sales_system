package com.sales.sales_system.service;

import com.sales.sales_system.dto.user.UserUpdateDTO;
import com.sales.sales_system.entity.Customer;
import com.sales.sales_system.entity.Role;
import com.sales.sales_system.error_handling.db_errors.DatabaseErrorsResponse;
import com.sales.sales_system.error_handling.db_errors.DuplicateConstraintException;
import com.sales.sales_system.error_handling.db_errors.NotFoundException;
import com.sales.sales_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        this.checkUniqueUser(newCustomer.getUser());

        Role role = roleService.findById(newCustomer.getRole().getId());
        newCustomer.setRole(role);

        return this.customerRespository.save(newCustomer);
    }

    public Customer updateCustomer(Customer updatedCustomer, Long id)
    {
        this.checkUniqueDocument(updatedCustomer.getDocument(), id);
        this.checkUniqueUser(updatedCustomer.getUser(), id);

        Role role = this.roleService.findById(updatedCustomer.getRole().getId());
        Customer customerBD = this.findById(id);

        customerBD.setName(updatedCustomer.getName());
        customerBD.setRole(role);

        if (!(updatedCustomer.getPassword().isEmpty()))
            customerBD.setPassword(updatedCustomer.getPassword());

        if (updatedCustomer.getPhone() != null)
            customerBD.setPhone(updatedCustomer.getPhone());

        Customer customer = this.customerRespository.save(customerBD);

        return this.findById(customer.getId());
    }

    public List<Customer> findAll()
    {
        return this.customerRespository.findAll();
    }

    public Customer findById(Long id)
    {
        Optional<Customer> customer = this.customerRespository.findById(id);

        if (customer.isEmpty())
            throw new NotFoundException("User with id: '" + id + "' NOT FOUND");

        return customer.get();
    }

    public Customer deleteById(Long id)
    {
        Customer customer = this.findById(id);

        this.customerRespository.deleteById(customer.getId());

        return customer;
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

        if (customer != null && (customer.getDocument().equals(customerDocument)))
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
