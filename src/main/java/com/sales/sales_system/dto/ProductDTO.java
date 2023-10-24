package com.sales.sales_system.dto;

import com.sales.sales_system.entity.Category;
import com.sales.sales_system.entity.Detail;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public class ProductDTO
{
    private Long id;
    @NotNull(message = "category_id is required")
    private Long category_id;
    @NotBlank(message = "Name of product is required")
    private String name;
    @NotNull(message = "Price of product is required")
    @Min(value = 1, message = "The price of the product must be greater than zero(0)")
    private Integer price;
    private Category category;
    private Set<Detail> details;

    /*
    * Is necessary the constructor without parameters
    * for the deserialization of JSON.
    * */
    public ProductDTO() { }
    
    public ProductDTO(Long id)
    {
        this(id, null, null, null);
    }

    public ProductDTO(Long id, Long category_id)
    {
        this(id, category_id, null, null);
    }

    public ProductDTO(Long id, String name)
    {
        this(id, null, name, null);
    }

    public ProductDTO(Long id, Long category_id, String name)
    {
        this(id, category_id, name, null);
    }

    public ProductDTO(Long id, Long category_id, String name, Integer price)
    {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
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

    public Set<Detail> getDetails() {
        return details;
    }

    public void setDetails(Set<Detail> details) {
        this.details = details;
    }
}
