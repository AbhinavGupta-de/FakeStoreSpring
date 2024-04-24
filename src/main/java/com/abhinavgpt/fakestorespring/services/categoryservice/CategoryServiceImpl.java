package com.abhinavgpt.fakestorespring.services.categoryservice;

import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<String> getCategoryNames() {
        return categoryRepository.findAll().stream().map(Category::getName).filter(Objects::nonNull).toList();
    }

    @Override
    public Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName).orElse(null);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        category.ifPresent(categoryRepository::delete);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
