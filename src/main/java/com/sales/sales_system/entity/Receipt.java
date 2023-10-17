package com.sales.sales_system.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "receipts")
public class Receipt
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /*
    * +------------+
    * | ONE TO ONE |
    * +------------+
    *
    * In this case this entity children and has the FK.
    * */
    @OneToOne(
        optional = false, // This means that the relationship is required, so is NOT NULL
        fetch = FetchType.LAZY // Returns only the data explicitly accessed.
    )
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToMany(mappedBy = "receipt") // This is the property that references the parent
    private Set<Detail> details;

    @Column(name = "total_payment", nullable = false)
    private Long totalPayment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_payment")
    private Date datePayment;

    public Receipt() {
    }

    public Receipt(Long id, Order order, Long totalPayment, Date datePayment) {
        this.id = id;
        this.order = order;
        this.totalPayment = totalPayment;
        this.datePayment = datePayment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<Detail> getDetails() {
        return details;
    }

    public void setDetails(Set<Detail> details) {
        this.details = details;
    }

    public Long getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Long totalPayment) {
        this.totalPayment = totalPayment;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }
}
