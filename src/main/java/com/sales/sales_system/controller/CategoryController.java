package com.sales.sales_system.controller;

import com.sales.sales_system.entity.Category;
import com.sales.sales_system.response.Response;
import com.sales.sales_system.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "${apiPrefix}")
public class CategoryController
{
    private CategoryService categoryService;

    @Autowired
    private CategoryController(CategoryService categoryService)
    {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> findAll()
    {
        return this.categoryService.findAll();
    }

    @GetMapping("/categories/{name}/name")
    public Category findByName(@PathVariable String name)
    {
        return this.categoryService.findByName(name);
    }

    @GetMapping("/categories/{id}")
    // the "?" Represents an unknown type for the generic type
    public Response<?> findById(@PathVariable Long id)
    {
        Optional<Category> category = this.categoryService.findById(id);

        if (category.isPresent())
        {
            // We have to define the type of the value to create the instance
            // of the class, in another way we can't create it.
            Response<Category> response = new Response<>("successful");
            response.setBody(category.get());

            return response;
        }
        else
        {
            Response<String> response = new Response<>("unsuccessful");
            response.setBody("Category Not Found");

            return response;
        }
    }

    @PostMapping("/categories")
    public Category save(@RequestBody Category category)
    {
        Category newCategory = this.categoryService.save(category);

        return category;
    }
}
