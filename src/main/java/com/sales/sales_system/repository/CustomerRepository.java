package com.sales.sales_system.repository;

import com.sales.sales_system.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    public Customer findCustomerByDocument(Long document);
    public Customer findCustomerByUser(String user);
}
