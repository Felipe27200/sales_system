package com.sales.sales_system.repository;

import com.sales.sales_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    public Product findProductByName(String name);

    /*
    * This code uses HQL (Hibernate Query Language)
    * to create the query with an INNER JOIN to fetch
    * the products based on their category_id.
    * */
    @Query(
        value = "SELECT p FROM Product p" +
            "\nJOIN Category c ON c.id = p.category.id" +
            "\nWHERE c.id = ?1"
    )
    public List<Product> findProductByCategory_id(Long id);
}
