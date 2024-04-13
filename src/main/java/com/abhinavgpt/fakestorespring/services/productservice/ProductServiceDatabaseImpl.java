package com.abhinavgpt.fakestorespring.services.productservice;

import com.abhinavgpt.fakestorespring.exceptions.ProductNotFoundException;
import com.abhinavgpt.fakestorespring.models.Product;
import com.abhinavgpt.fakestorespring.repository.ProductRepository;
import com.abhinavgpt.fakestorespring.services.categoryservice.CategoryService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProductServiceDatabaseImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceDatabaseImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) throws ProductNotFoundException {
        return productRepository.findByCategory(categoryService.getCategory(categoryName)).orElseThrow(() -> new ProductNotFoundException("Product with category " + categoryName + " not found"));
    }

    @Override
    public List<Product> getLimitedProducts(int limit) {
        return productRepository.findAll().subList(0, limit);
    }

    @Override
    public List<Product> getSortedProducts(String sort) {
        return getProducts();
    }
}
