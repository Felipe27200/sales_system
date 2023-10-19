package com.sales.sales_system.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
    name = "roles",
    uniqueConstraints = @UniqueConstraint(name = "role_name", columnNames = "name")
)
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private List<Customer> customers;

    public Role() { }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
