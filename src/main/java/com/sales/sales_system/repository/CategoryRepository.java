package com.sales.sales_system.repository;

import com.sales.sales_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
    public Category findByName(String name);
}
