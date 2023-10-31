package com.sales.sales_system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Table(
    name = "customers",
    /*
    * Define a unique constraint, the name is the name of the unique key
    * and columNames is for the columns where we will apply the constraint.
    * */
    uniqueConstraints = {
        @UniqueConstraint(name = "customer_document", columnNames = { "document" }),
        @UniqueConstraint(name = "customer_user", columnNames = { "user" })
    }
)
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
    private Long phone;
    @Column(nullable = false)
    private String password;
    @Column(name = "user", nullable = false)
    private String user;

    @ManyToOne()
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public Customer() { }

    public Customer(Long id, String name, Long document, Long phone)
    {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
    }

    public Customer(Long id, String name, Long document, Long phone, String password, String user) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.password = password;
        this.user = user;
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
