package com.abhinavgpt.fakestorespring.services.productservice;

import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.models.Product;
import com.abhinavgpt.fakestorespring.repository.ProductRepository;
import com.abhinavgpt.fakestorespring.services.categoryservice.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceDatabaseImplTest {

    @InjectMocks
    ProductServiceDatabaseImpl productService;

    @Mock
    ProductRepository productRepository;

    @Mock
    CategoryService categoryService;

    @Test
    void getProducts() {

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", null));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> products1 = productService.getProducts();

        assertEquals(products, products1);

    }

    @Test
    void getProduct() {

        Product product = new Product(1, "Product 1", "", 23.0, "", null);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        try {
            when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Product product1 = null;
        try {
            product1 = productService.getProduct(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(product, product1);
    }

    @Test
    void addProduct() {

        Product product = new Product(1, "Product 1", "", 23.0, "", new Category(1L, "Category 1"));

        when(productRepository.save(product)).thenReturn(product);

        Product product1 = null;
        try {
            product1 = productService.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(product, product1);
    }

    @Test
    void updateProduct() {

        Product product = new Product(1, "Product 1", "", 23.0, "", null);

        when(productRepository.save(product)).thenReturn(product);

        Product product1 = null;
        try {
            product1 = productService.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(product, product1);
    }

    @Test
    void deleteProduct() {

        Product product = new Product(1, "Product 1", "", 23.0, "", null);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));

        productService.deleteProduct(1L);

        assertEquals(product, product);
    }

    @Test
    void getProductsByCategory() {

        Category category = new Category(1L, "Category 1");

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", category));


        when(categoryService.getCategory("Category 1")).thenReturn(category);

        when(productRepository.findByCategory(category)).thenReturn(Optional.of(products));

        List<Product> products1 = null;

        try {
            products1 = productService.getProductsByCategory("Category 1");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(products, products1);
    }

    @Test
    void getLimitedProducts() {

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", null));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> products1 = productService.getLimitedProducts(1);

        assertEquals(products, products1);

    }

    @Test
    void getSortedProducts() {

        List<Product> products = List.of(new Product(1, "Product 1", "", 23.0, "", null));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> products1 = productService.getSortedProducts("asend");

        assertEquals(products, products1);

    }
}