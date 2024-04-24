package com.abhinavgpt.fakestorespring.services.categoryservice;

import com.abhinavgpt.fakestorespring.models.Category;

import java.util.List;

public interface CategoryService {

    public List<String> getCategoryNames();

    public Category getCategory(String categoryName);

    public Category addCategory(Category category);

    public void deleteCategory(String categoryName);

    public Category getCategoryById(Long id);

}
