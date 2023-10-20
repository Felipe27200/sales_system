package com.sales.sales_system.repository;

import com.sales.sales_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
    public Category findByName(String name);

    @Query(
        value = "SELECT * FROM categories WHERE name LIKE %?1%",
        nativeQuery = true
    )
    public List<Category> findByNameCoincidence(String name);
}
