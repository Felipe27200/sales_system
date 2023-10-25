package com.sales.sales_system.controller;

import com.sales.sales_system.dto.ProductDTO;
import com.sales.sales_system.entity.Category;
import com.sales.sales_system.entity.Product;
import com.sales.sales_system.response.Response;
import com.sales.sales_system.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(path = "/api")
public class ProductController
{
    private ModelMapper modelMapper;
    private ProductService productService;

    @Autowired
    public ProductController(ModelMapper modelMapper, ProductService productService)
    {
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @PostMapping("/products")
    public Response<?> save(@Valid @RequestBody ProductDTO productDTO)
    {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(new Category(productDTO.getCategory_id()));

        Product newProduct = this.productService.saveProduct(product);

        if (newProduct != null)
            return new Response<>(newProduct, "successful");
        else
            return new Response<>("Product create failed", "unsuccessful");
    }

    @GetMapping("/products")
    public Response<?> findAll()
    {
        return new Response<>(this.productService.getAll(), "successful");
    }

    @GetMapping("/products/{id}")
    public Response<?> findById(@PathVariable Long id)
    {
        return new Response<>(this.productService.findById(id), "successful");
    }

    @GetMapping("/products/{id}/category")
    public Response<?> findProductsByCategory(@PathVariable Long id)
    {
        return new Response<>(this.productService.findProductsByCategory(id), "successful");
    }

    @PutMapping("/products/{id}")
    public Response<?> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id)
    {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(new Category(productDTO.getCategory_id()));

        Product productUpdated = this.productService.updateProduct(product, id);

        if (productUpdated != null)
            return new Response<>(productUpdated, "successful");
        else
            return new Response<>("Product update failed", "unsuccessful");
    }

    @DeleteMapping("/products/{id}")
    public Response<?> deleteProduct(@PathVariable Long id)
    {
        Product product = this.productService.deleteProduct(id);

        if (product == null)
            return new Response<>("Delete product failed", "unsuccessful");
        else
            return new Response<>("The product: " + product.getName() + " was deleted", "successful");
    }
}
