package com.sales.sales_system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP) // Define the type of the date in this case hh:mm:ss 00:00:00
    @Column(name = "date_order", nullable = false)
    private Date dateOrder;
    @Column(
        name = "status",
        length = 2
    )
    @ColumnDefault("0") // Set the default value for the column
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "customer_id",
        referencedColumnName = "id",
        nullable = false
    )
    private Customer customer;

    public Order() {
    }

    public Order(Long id, Date dateOrder, int status) {
        this.id = id;
        this.dateOrder = dateOrder;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
