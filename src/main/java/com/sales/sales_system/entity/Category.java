package com.sales.sales_system.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
    name = "categories",
    // Set the unique value for a column
    uniqueConstraints = @UniqueConstraint(name="category_name", columnNames = "name")
)
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    /*
    * +-------------+
    * | ONE TO MANY |
    * +-------------+
    *
    * The value of mappedBy references the property in the
    * child class that references the child table, so Category
    * is the parent class and table, and Product has a property
    * named category the make the relationship.
    * */
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() { }

    public Category(String name) {
        this(null, name);
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
