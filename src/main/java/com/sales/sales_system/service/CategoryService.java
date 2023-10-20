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
        Category searchCategory = this.findByName(newCategory.getName());

        if (searchCategory != null)
            return null;

        Category category = this.categoryRepository.save(newCategory);

        return category;
    }

    @Transactional
    public Category update(Category category, Long id)
    {
        Optional<Category> searchCategory = this.findById(id);

        if (!(searchCategory.isPresent()))
            return null;

        Category categoryFound = searchCategory.get();
        categoryFound.setName(category.getName());

        return this.categoryRepository.save(categoryFound);
    }

    @Transactional
    public String deleteById(Long id)
    {
        Optional<Category> category = this.findById(id);

        if(category.isEmpty())
            return "";

        this.categoryRepository.deleteById(id);

        return category.get().getName();
    }

    public List<Category> findByNameCoincidence(String name)
    {
        List<Category> categories = this.categoryRepository.findByNameCoincidence(name);

        return categories;
    }

}
