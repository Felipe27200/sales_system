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

        this.isUniqueName(product.getName());

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

    @Transactional
    public Product updateProduct(Product product, Long id)
    {
        this.isUniqueName(product.getName(), id);

        Product productDB = this.findById(id);

        productDB.setCategory(this.findCategory(product.getCategory().getId()));
        productDB.setName(product.getName());
        productDB.setPrice(product.getPrice());

        return this.productRepository.save(productDB);
    }

    @Transactional
    public Product deleteProduct(Long id)
    {
        Product product = this.findById(id);

        this.productRepository.deleteById(id);

        return product;
    }

    public Category findCategory(Long id)
    {
        Optional<Category> category = this.categoryService.findById(id);

        if (category.isPresent())
            return category.get();
        else
           return null;
    }

    public List<Product> findProductsByCategory(Long id)
    {
        Category category = this.findCategory(id);

        if (category == null)
            throw new NotFoundException("Category with the id '" + id + "' NOT FOUND");

        List<Product> products = this.productRepository.findProductByCategory_id(id);

        return products;
    }

    public void isUniqueName(String name)
    {
        if (this.findProductByName(name) != null)
            throw new DuplicateConstraintException("The name: " + name + " already exists.");
    }

    public void isUniqueName(String name, Long id)
    {
        Product product = this.findProductByName(name);

        if (product != null && !(product.getId().equals(id)))
            throw new DuplicateConstraintException("The name: " + name + " already exists.");
    }
}
