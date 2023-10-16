package com.sales.sales_system.entity;

import jakarta.persistence.*;

@Entity
@Table(
    name = "products",
    uniqueConstraints = @UniqueConstraint(name = "category_name", columnNames = "name")
)
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price")
    private Integer price;

    /*
    * +-------------+
    * | MANY TO ONE |
    * +-------------+
    *
    * The child must be the owning side of the
    * relationship for avoid inconsistencies.
    *
    * For this we use the @ManytoOne and
    * give it more details with the @JoinColumn
    *
    * The owning side is defined by the entity
    * that have the @JoinColumn, also but table
    * can have it.
    * */
    @ManyToOne(fetch = FetchType.LAZY) // Only get the data from one table of the relationship.
    @JoinColumn(
        name = "category_id", // name property defines the name of the FK for this entity.
        referencedColumnName = "id", // Name of the primary key in the parent table
        nullable = false
    )
    private Category category;

    public Product() { }
    public Product(Long id, String name, Integer price, Category category)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
