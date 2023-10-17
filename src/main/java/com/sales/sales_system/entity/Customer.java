package com.sales.sales_system.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(
        name = "name",
        nullable = false,
        length = 60
    )
    private String name;
    @Column(name = "document", nullable = false)
    private Long document;
    @Column(name = "phone", length = 10)
    private int phone;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer() { }

    public Customer(Long id, String name, Long document, int phone)
    {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocument() {
        return document;
    }

    public void setDocument(Long document) {
        this.document = document;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
