package com.abhinavgpt.fakestorespring.services.productservice;

import com.abhinavgpt.fakestorespring.exceptions.CategoryNotFoundException;
import com.abhinavgpt.fakestorespring.exceptions.ProductNotFoundException;
import com.abhinavgpt.fakestorespring.models.Product;

import java.util.List;

public sealed interface ProductService permits ProductServiceImpl, ProductServiceDatabaseImpl {
    public List<Product> getProducts();

    public Product getProduct(Long id) throws ProductNotFoundException;

    public Product addProduct(Product product) throws CategoryNotFoundException;

    public Product updateProduct(Product product);

    public void deleteProduct(Long id);

    public List<Product> getProductsByCategory(String categoryName) throws ProductNotFoundException;

    public List<Product> getLimitedProducts(int limit);

    public List<Product> getSortedProducts(String sort);

}
