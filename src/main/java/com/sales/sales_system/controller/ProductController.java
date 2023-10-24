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

        return new Response<>(newProduct, "successful");
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
}
