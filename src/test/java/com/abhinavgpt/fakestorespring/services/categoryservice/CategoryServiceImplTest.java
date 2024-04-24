package com.abhinavgpt.fakestorespring.services.categoryservice;

import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceImplTest {

    @InjectMocks
    CategoryServiceImpl categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Test
    void getCategoryNames() {
        List<Category> categories = List.of(new Category(1, "Category 1"), new Category(2, "Category 2"));
        when(categoryRepository.findAll()).thenReturn(categories);

        List<String> categoriesR = categoryService.getCategoryNames();

        assertEquals(categories.stream().map(Category::getName).filter(Objects::nonNull).toList(), categoriesR);
    }

    @Test
    void getCategory() {
        Category category = new Category(1, "Category 1");
        when(categoryRepository.findByName("Category 1")).thenReturn(java.util.Optional.of(category));

        Category categoryR = categoryService.getCategory("Category 1");

        assertEquals(category, categoryR);
    }

    @Test
    void addCategory() {
        Category category = new Category(1, "Category 1");
        when(categoryRepository.save(category)).thenReturn(category);

        Category categoryR = categoryService.addCategory(category);

        assertEquals(category, categoryR);
    }

    @Test
    void deleteCategory() {
        Category category = new Category(1, "Category 1");
        when(categoryRepository.findByName("Category 1")).thenReturn(java.util.Optional.of(category));

        categoryService.deleteCategory("Category 1");

        Mockito.verify(categoryRepository, times(1)).delete(category);
    }

    @Test
    void getCategoryById() {
        Category category = new Category(1, "Category 1");
        when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));

        Category categoryR = categoryService.getCategoryById(1L);

        assertEquals(category, categoryR);
    }
}