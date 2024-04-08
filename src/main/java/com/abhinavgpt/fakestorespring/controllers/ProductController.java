package com.abhinavgpt.fakestorespring.controllers;

import com.abhinavgpt.fakestorespring.exceptions.ProductNotFoundException;
import com.abhinavgpt.fakestorespring.models.Product;
import com.abhinavgpt.fakestorespring.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return productService.getCategories();
    }

    @GetMapping("/limited/{limit}")
    public List<Product> getLimitedProducts(@PathVariable int limit) {
        return productService.getLimitedProducts(limit);
    }

    @GetMapping("/sorted")
    public List<Product> getSortedProducts(@RequestParam("order") String sort) {
        return productService.getSortedProducts(sort);
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) throws ProductNotFoundException {
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "Product deleted successfully";
    }

    @PostMapping("/add")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

}
