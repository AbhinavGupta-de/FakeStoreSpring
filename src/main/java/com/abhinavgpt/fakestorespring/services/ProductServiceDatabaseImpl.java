package com.abhinavgpt.fakestorespring.services;

import com.abhinavgpt.fakestorespring.exceptions.ProductNotFoundException;
import com.abhinavgpt.fakestorespring.models.Product;

import java.util.List;

public class ProductServiceDatabaseImpl implements ProductService{

    @Override
    public List<Product> getProducts() {
        return List.of();
    }

    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) throws ProductNotFoundException {
        return List.of();
    }

    @Override
    public List<String> getCategories() {
        return List.of();
    }

    @Override
    public List<Product> getLimitedProducts(int limit) {
        return List.of();
    }

    @Override
    public List<Product> getSortedProducts(String sort) {
        return List.of();
    }
}
