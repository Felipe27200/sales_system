package com.sales.sales_system.entity;

import jakarta.persistence.*;

import java.util.Date;

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

    @Column(name = "total_payment", nullable = false)
    private Long totalPayment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_payment")
    private Date datePayment;

}
