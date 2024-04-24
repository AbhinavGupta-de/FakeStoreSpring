package com.abhinavgpt.fakestorespring.controllers;

import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.services.categoryservice.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryControllerTest {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    @Test
    void getCategories() {
        when(categoryService.getCategoryNames()).thenReturn(List.of("Category 1", "Category 2"));

        List<String> categories = categoryController.getCategories();

        assertNotNull(categories);
    }

    @Test
    void addCategory() {
        when(categoryService.addCategory(any(Category.class))).thenReturn(new Category(1, "Category 1"));

        Category category = categoryController.addCategory(new Category(1, "Category 1"));

        assertNotNull(category);
    }

    @Test
    void deleteCategory() {

        String categoryName = "Category 1";

        categoryController.deleteCategory(categoryName);

        Mockito.verify(categoryService, times(1)).deleteCategory(categoryName);

    }
}