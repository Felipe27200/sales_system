package com.sales.sales_system.controller;

import com.sales.sales_system.entity.Category;
import com.sales.sales_system.response.Response;
import com.sales.sales_system.service.CategoryService;
import jakarta.validation.Valid;
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
    public Response<?> findAll()
    {
        List<Category> categories = this.categoryService.findAll();

        if (categories != null && !categories.isEmpty())
            return new Response<List<Category>>(categories, "successful"); // Define the generic type as list of Category
        else
            return new Response<>("Category Not Found", "unsuccessful");
    }

    @GetMapping("/categories/{name}/name")
    public Response<?> findByName(@PathVariable String name)
    {
        Category category = this.categoryService.findByName(name);

        if (category == null)
            return new Response<>("Category NOT FOUND", "unsuccessful");
        else
            return new Response<>(category, "successful");
    }

    @GetMapping("/categories/{name}/coincidence")
    public Response<?> findByNameCoincidence(@PathVariable String name)
    {
        List<Category> categories = this.categoryService.findByNameCoincidence(name);

        if (categories != null && !categories.isEmpty())
            return new Response<>(categories, "successful");
        else
            return new Response<>("Categories with name: '" + name + "' NOT FOUND", "unsuccessful");
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
            return new Response<>(category.get(), "successful");
        }
        else
        {
            return new Response<>("Category Not Found", "unsuccessful");
        }
    }

    @PostMapping("/categories")
    /*
    * The @Valid annotation mark the argument as necessary to validate
    * and the Spring validation verify its fields.
    * */
    public Response<?> save(@Valid @RequestBody Category category)
    {
        Category newCategory = this.categoryService.save(category);

        if (newCategory == null)
            return new Response<>("The category is already registered", "unsuccessful");
        else
            return new Response<>(newCategory, "successful");
    }

    @PutMapping("categories/{id}")
    public Response<?> updateCategory(@Valid @RequestBody Category category, @PathVariable Long id)
    {
        Category updatedCategory = this.categoryService.update(category, id);

        if (updatedCategory == null)
            return new Response<>("The category doesn't exists", "unsuccessful");
        else
            return new Response<>(updatedCategory, "successful");
    }

    @DeleteMapping("/categories/{id}")
    public Response<String> deleteById(@PathVariable Long id)
    {
        String categoryName = this.categoryService.deleteById(id);

        if (categoryName.isEmpty())
            return new Response<>("Category Not Found", "unsuccessful");
        else
            return new Response<>("Category: " + categoryName + " was deleted.", "successful");
    }
}
