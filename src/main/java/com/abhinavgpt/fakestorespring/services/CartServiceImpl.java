package com.abhinavgpt.fakestorespring.services;

import com.abhinavgpt.fakestorespring.dtos.CartReceiveDTO;
import com.abhinavgpt.fakestorespring.dtos.ProductCartDTO;
import com.abhinavgpt.fakestorespring.exceptions.CartNotFoundException;
import com.abhinavgpt.fakestorespring.exceptions.ProductNotFoundException;
import com.abhinavgpt.fakestorespring.models.Cart;
import com.abhinavgpt.fakestorespring.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final String url = "https://fakestoreapi.com/carts";
    private final RestTemplate restTemplate = new RestTemplate();

    private final ProductService productService;

    public CartServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    private Cart mapToCart(CartReceiveDTO cartRecieveDTO) {
        List<Product> products = mapToProduct(cartRecieveDTO.products());
        return new Cart(cartRecieveDTO.id(), cartRecieveDTO.userId(), cartRecieveDTO.date(), products);
    }

    private List<Product> mapToProduct(List<ProductCartDTO> products) {
        List<Product> productList = new ArrayList<>();

        for (ProductCartDTO productCartDTO : products) {
            Product product = null;
            try {
                product = productService.getProduct(productCartDTO.productId());
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e);
            }
            productList.add(product);
        }

        return productList;
    }


    @Override
    public List<Cart> getAllProducts() {

        List<CartReceiveDTO> cartFetchDTO = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<CartReceiveDTO>>() {
        }).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();
    }

    @Override
    public Cart getCart(long id) throws CartNotFoundException {

        CartReceiveDTO cartRecieveDTO = restTemplate.getForObject(url + "/" + id, CartReceiveDTO.class);

        if (cartRecieveDTO == null) {
            throw new CartNotFoundException("Cart with id " + id + " not found.");
        }

        return mapToCart(cartRecieveDTO);
    }

    @Override
    public List<Cart> limitedCarts(long limit) {
        List<CartReceiveDTO> cartFetchDTO = restTemplate.exchange(url + "?limit=" + limit, HttpMethod.GET, null, new ParameterizedTypeReference<List<CartReceiveDTO>>() {
        }).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();
    }

    @Override
    public List<Cart> sortedCarts(String order) {

        List<CartReceiveDTO> cartFetchDTO = restTemplate.exchange(url + "?sort=" + order, HttpMethod.GET, null, new ParameterizedTypeReference<List<CartReceiveDTO>>() {
        }).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();

    }

    @Override
    public List<Cart> inDateRange(String start, String end) {

        List<CartReceiveDTO> cartFetchDTO = restTemplate.exchange(url + "?startdate=" + start + "&enddate=" + end, HttpMethod.GET, null, new ParameterizedTypeReference<List<CartReceiveDTO>>() {
        }).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();

    }

    @Override
    public List<Cart> userCart(long userId) {
        List<CartReceiveDTO> cartFetchDTO = restTemplate.exchange(url + "/user/" + userId, HttpMethod.GET, null, new ParameterizedTypeReference<List<CartReceiveDTO>>() {
        }).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();
    }

    @Override
    public void addNewCart(Cart cart) {
        CartReceiveDTO sendCart = mapToCardDTO(cart);
        sendCart = restTemplate.postForObject(url, sendCart, CartReceiveDTO.class);

//        The cart could be returned to the user as well but no need to do that right now

    }

    @Override
    public void updateProduct(Cart cart) {
        CartReceiveDTO sendCart = mapToCardDTO(cart);
        restTemplate.put(url + "/" + sendCart.date(), sendCart);
    }

    private CartReceiveDTO mapToCardDTO(Cart cart) {
        List<ProductCartDTO> products = mapToProductDTO(cart.getProducts());
        return new CartReceiveDTO(cart.getId(), cart.getUserId(), cart.getDate(), products);

    }

    private List<ProductCartDTO> mapToProductDTO(List<Product> products) {
        List<ProductCartDTO> productCartDTOList = new ArrayList<>();

        for (Product product : products) {
            productCartDTOList.add(new ProductCartDTO(product.getId(), 1));
        }

        return productCartDTOList;
    }

    @Override
    public void deleteCart(long id) {
        restTemplate.delete(url + "/" + id);
    }
}