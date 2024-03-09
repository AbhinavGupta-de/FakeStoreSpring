package com.abhinavgpt.fakestorespring.services;

import org.springframework.web.client.RestTemplate;

public class ProductService {

    private RestTemplate restTemplate = new RestTemplate();
    // Alows to send API requests to any URL

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) {

        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());

        return product;
    }

    @Override
    public Product createProduct(Product product) {

//        restTemplate.postForObject()
        return null;

}
