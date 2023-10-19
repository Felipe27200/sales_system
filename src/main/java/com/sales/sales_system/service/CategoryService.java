package com.sales.sales_system.service;

import com.sales.sales_system.entity.Category;
import com.sales.sales_system.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService
{
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository)
    {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll()
    {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id)
    {
        return categoryRepository.findById(id);
    }

    public Category findByName(String name)
    {
        return this.categoryRepository.findByName(name);
    }

    @Transactional
    public Category save(Category newCategory)
    {
        Category searchCategory = this.categoryRepository.findByName(newCategory.getName());

        if (searchCategory != null)
            return searchCategory;

        Category category = this.categoryRepository.save(newCategory);

        return category;
    }
}
