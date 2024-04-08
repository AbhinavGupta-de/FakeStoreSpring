package com.abhinavgpt.fakestorespring.services;

import com.abhinavgpt.fakestorespring.dtos.ProductFetchDTO;
import com.abhinavgpt.fakestorespring.exceptions.ProductNotFoundException;
import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final String url = "https://fakestoreapi.com/products";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Product> getProducts() {

        List<ProductFetchDTO> productFetchDTOS = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductFetchDTO>>() {}).getBody();

        assert productFetchDTOS != null;

        return productFetchDTOS.stream().map(this::mapToProduct).toList();
    }

    private Product mapToProduct(ProductFetchDTO productFetchDTO) {
        Category category = new Category(1L, productFetchDTO.category());
        return new Product(productFetchDTO.id(), productFetchDTO.title(), productFetchDTO.description(), productFetchDTO.price(), category , productFetchDTO.image());
    }

    @Override
    public Product getProduct(Long id) throws ProductNotFoundException {

        ProductFetchDTO productFetchDTO = restTemplate.getForObject(url + "/" + id, ProductFetchDTO.class);

        if (productFetchDTO == null) {
            throw new ProductNotFoundException("Product with id: " + id + " not found");
        }

        return mapToProduct(productFetchDTO);
    }


    private ProductFetchDTO mapToProductFetchDTO(Product product) {
        return new ProductFetchDTO(product.id(), product.title(), product.description(), product.price(), product.category().name(), product.imageUrl());
    }


    @Override
    public Product addProduct(Product product) {


        ProductFetchDTO response = restTemplate.postForObject(url, mapToProductFetchDTO(product), ProductFetchDTO.class);

        assert response != null;

        return mapToProduct(response);
    }

    @Override
    public Product updateProduct(Product product) {

        restTemplate.put(url + "/" + product.id(), mapToProductFetchDTO(product));

        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) throws ProductNotFoundException {
        List<ProductFetchDTO> productFetchDTOS = restTemplate.exchange(
                url + "/category/" + categoryName,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductFetchDTO>>() {}).getBody();

        if(productFetchDTOS == null) {
            throw new ProductNotFoundException("Products with category: " + categoryName + " not found");
        }

        return productFetchDTOS.stream().map(this::mapToProduct).toList();
    }

    @Override
    public List<String> getCategories() {

        List<String> categories = restTemplate.exchange(
                url + "/categories",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<String>>() {}).getBody();

        assert categories != null;

        return categories;
    }

    @Override
    public List<Product> getLimitedProducts(int limit) {

        List<ProductFetchDTO> productFetchDTOS = restTemplate.exchange(
                url + "?limit=" + limit,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductFetchDTO>>() {}).getBody();

        assert productFetchDTOS != null;

        return productFetchDTOS.stream().map(this::mapToProduct).toList();

    }

    @Override
    public List<Product> getSortedProducts(String sort) {

        List<ProductFetchDTO> productFetchDTOS = restTemplate.exchange(
                url + "?sort=" + sort,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductFetchDTO>>() {}).getBody();

        assert productFetchDTOS != null;

        return productFetchDTOS.stream().map(this::mapToProduct).toList();

    }
}
