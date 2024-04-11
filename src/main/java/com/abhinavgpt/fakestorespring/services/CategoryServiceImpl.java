package com.abhinavgpt.fakestorespring.services;

import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImpl implements  CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getCategoryNames() {
        return categoryRepository.findAll().stream().map(Category::getName).filter(Objects::nonNull).toList();
    }

    public Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName).orElse(null);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(String categoryName) {
        categoryRepository.deleteByName(categoryName);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
