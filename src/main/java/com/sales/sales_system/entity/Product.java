package com.sales.sales_system.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    @NotBlank(message = "Name of product is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Price of product is required")
    @Min(value = 1)

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
    *
    * If the relationship is unidirectional
    * is not necessary define the fetch type.
    * */
    @ManyToOne() // Only get the data from one table of the relationship.
    @JoinColumn(
        name = "category_id", // name property defines the name of the FK for this entity.
        referencedColumnName = "id", // Name of the primary key in the parent table
        nullable = false
    )

    /*
    * This make that this reference will be omitted
    * when the json will be created.
    * */
    private Category category;

/*
    @OneToMany(mappedBy = "product")
    // @JsonBackReference("details")
    private Set<Detail> details;
*/

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

/*
    public Set<Detail> getDetails() {
        return details;
    }

    public void setDetails(Set<Detail> details) {
        this.details = details;
    }
*/
}
