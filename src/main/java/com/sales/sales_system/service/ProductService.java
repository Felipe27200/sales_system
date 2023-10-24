package com.sales.sales_system.service;

import com.sales.sales_system.entity.Category;
import com.sales.sales_system.entity.Product;
import com.sales.sales_system.error_handling.db_errors.DuplicateConstraintException;
import com.sales.sales_system.error_handling.db_errors.NotFoundException;
import com.sales.sales_system.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    private ProductRepository productRepository;
    private CategoryService categoryService;

    @Autowired
    public ProductService(
        CategoryService categoryService,
        ProductRepository productRepository
    )
    {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Transactional
    public Product saveProduct(Product product)
    {
        Optional<Category> category = this.categoryService.findById(product.getCategory().getId());
        product.setCategory(category.get());

        Product findProduct = this.findProductByName(product.getName());

        if (findProduct != null)
            throw new DuplicateConstraintException("The name '" + product.getName() + "' already exists");

        return this.productRepository.save(product);
    }

    public Product findProductByName(String name)
    {
        Product product = this.productRepository.findProductByName(name);

        return product;
    }

    public Product findById(Long id)
    {
        Optional<Product> product = this.productRepository.findById(id);

        if (product.isEmpty() && !product.isPresent())
            throw new NotFoundException("Product with the id: " + id + " not found.");

        return product.get();
    }

    public List<Product> getAll()
    {
        return this.productRepository.findAll();
    }

}
