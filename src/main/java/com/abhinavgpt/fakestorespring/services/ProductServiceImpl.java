package com.abhinavgpt.fakestorespring.services;

import com.abhinavgpt.fakestorespring.dtos.ProductFetchDTO;
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
        Category category = new Category(1L, productFetchDTO.getCategory());
        category.setName(productFetchDTO.getCategory());
        return new Product(productFetchDTO.getId(), productFetchDTO.getTitle(), productFetchDTO.getDescription(), productFetchDTO.getPrice(), category , productFetchDTO.getImage());
    }

    @Override
    public Product getProduct(Long id) {

        ProductFetchDTO productFetchDTO = restTemplate.getForObject(url + "/" + id, ProductFetchDTO.class);

        assert productFetchDTO != null;

        return mapToProduct(productFetchDTO);
    }

    @Override
    public Product addProduct(Product product) {

//        Add product to the fakestoreapi
        ProductFetchDTO productFetchDTO = new ProductFetchDTO();
        productFetchDTO.setTitle(product.getTitle());
        productFetchDTO.setDescription(product.getDescription());
        productFetchDTO.setCategory(product.getCategory().getName());
        productFetchDTO.setPrice(product.getPrice());
        productFetchDTO.setImage(product.getImageUrl());

        ProductFetchDTO response = restTemplate.postForObject(url, productFetchDTO, ProductFetchDTO.class);

        assert response != null;

        return mapToProduct(response);
    }

    @Override
    public Product updateProduct(Product product) {
//        Update product to the fakestoreapi
        ProductFetchDTO productFetchDTO = new ProductFetchDTO();
        productFetchDTO.setId(product.getId());
        productFetchDTO.setTitle(product.getTitle());
        productFetchDTO.setDescription(product.getDescription());
        productFetchDTO.setCategory(product.getCategory().getName());
        productFetchDTO.setPrice(product.getPrice());
        productFetchDTO.setImage(product.getImageUrl());

        restTemplate.put(url + "/" + product.getId(), productFetchDTO);

        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete(url + "/" + id);
    }

    @Override
    public List<Product> getProductsByCategory(String categoryName) {
//     https://fakestoreapi.com/products/category/jewelery
        List<ProductFetchDTO> productFetchDTOS = restTemplate.exchange(
                url + "/category/" + categoryName,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductFetchDTO>>() {}).getBody();

        assert productFetchDTOS != null;

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

//        https://fakestoreapi.com/products?limit=
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

//        https://fakestoreapi.com/products?sort=
        List<ProductFetchDTO> productFetchDTOS = restTemplate.exchange(
                url + "?sort=" + sort,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductFetchDTO>>() {}).getBody();

        assert productFetchDTOS != null;

        return productFetchDTOS.stream().map(this::mapToProduct).toList();

    }
}
