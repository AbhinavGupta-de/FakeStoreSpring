package com.abhinavgpt.fakestorespring.controllers;

import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<String> getCategories() {
        return categoryService.getCategoryNames();
    }

    @PostMapping("")
    public Category addCategory(@RequestBody Category category) {
        System.out.println(category.getId());
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/{categoryName}")
    public String deleteCategory(@PathVariable String categoryName) {
        try {
            categoryService.deleteCategory(categoryName);
        } catch (Exception e) {
            return "There was an error while deleting category";
        }

        return "Category deleted successfully";

    }

}
