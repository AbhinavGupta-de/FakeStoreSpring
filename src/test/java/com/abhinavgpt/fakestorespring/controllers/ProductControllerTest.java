package com.abhinavgpt.fakestorespring.controllers;

import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.models.Product;
import com.abhinavgpt.fakestorespring.services.productservice.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {


    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    void getAllProducts() {

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")));

        when(productService.getProducts()).thenReturn(products);

        List<Product> products1 = productController.getAllProducts();

        assertEquals(products, products1);
    }

    @Test
    void getLimitedProducts() {

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")));

        when(productService.getLimitedProducts(1)).thenReturn(products);

        List<Product> products1 = productController.getLimitedProducts(1);

        assertEquals(products, products1);

    }

    @Test
    void getSortedProducts() {

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")));

        when(productService.getSortedProducts("asend")).thenReturn(products);

        List<Product> products1 = productController.getSortedProducts("asend");

        assertEquals(products, products1);
    }

    @Test
    void getProductsByCategory() {

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")));
        try {
            when(productService.getProductsByCategory("Category 1")).thenReturn(products);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Product> products1 = null;
        try {
            products1 = productController.getProductsByCategory("Category 1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(products, products1);
    }

    @Test
    void getProduct() throws Exception {
        Product product = new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"));

        when(productService.getProduct(1L)).thenReturn(product);

        Product product1 = productController.getProduct(1L);

        assertEquals(product, product1);
    }

    @Test
    void deleteProduct() {
        Long productId = 1L;

        productController.deleteProduct(productId);

        Mockito.verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void addProduct() throws Exception {
        Product product = new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"));

        Mockito.when(productService.addProduct(any(Product.class))).thenReturn(product);

        Product addedProduct = productController.addProduct(product);

        assertEquals(product, addedProduct);
        Mockito.verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    void updateProduct() {
        Product product = new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"));

        Mockito.when(productService.updateProduct(any(Product.class))).thenReturn(product);

        Product updatedProduct = productController.updateProduct(product);

        assertEquals(product, updatedProduct);
        Mockito.verify(productService, times(1)).updateProduct(any(Product.class));
    }
}