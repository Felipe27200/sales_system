package com.sales.sales_system.product_test;

import com.sales.sales_system.entity.Product;
import com.sales.sales_system.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestProduct {
    // private ProductService productService;
    private ProductRepository productRepository;

    @Autowired
    public TestProduct(
        ProductRepository productRepository
    )
    {
        this.productRepository = productRepository;
    }

    @Test
    public void getProducts()
    {
        List<Product> products = this.productRepository.findAll();

        System.out.println("Products" + products);
    }
}
