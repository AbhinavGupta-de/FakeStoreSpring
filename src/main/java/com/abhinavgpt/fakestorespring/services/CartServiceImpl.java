package com.abhinavgpt.fakestorespring.services;

import com.abhinavgpt.fakestorespring.dtos.CartRecieveDTO;
import com.abhinavgpt.fakestorespring.models.Cart;
import com.abhinavgpt.fakestorespring.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartServiceImpl implements  CartService{

    private final String url = "https://fakestoreapi.com/carts";
    private final RestTemplate restTemplate = new RestTemplate();

    private Cart mapToCart(CartRecieveDTO cartRecieveDTO) {
        return new Cart(cartRecieveDTO.id(), cartRecieveDTO.userId(), cartRecieveDTO.date(), cartRecieveDTO.products());

    }


    @Override
    public List<Cart> getAllProducts() {

        List<CartRecieveDTO> cartFetchDTO = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartRecieveDTO>>() {
                }).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();
    }

    @Override
    public Cart getCart(long id) {

        CartRecieveDTO cartRecieveDTO = restTemplate.getForObject(url + "/" + id, CartRecieveDTO.class);

        assert cartRecieveDTO != null;

        return mapToCart(cartRecieveDTO);
    }

    @Override
    public List<Cart> limitedCarts(long limit) {
        List<CartRecieveDTO> cartFetchDTO = restTemplate.exchange(
                url + "?limit=" + limit,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartRecieveDTO>>() {}).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();
    }

    @Override
    public List<Cart> sortedCarts(String order) {

        List<CartRecieveDTO> cartFetchDTO = restTemplate.exchange(
                url + "?sort=" + order,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartRecieveDTO>>() {}).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();

    }

    @Override
    public List<Cart> inDateRange(String start, String end) {

        List<CartRecieveDTO> cartFetchDTO = restTemplate.exchange(
                url + "?startdate=" + start + "&enddate=" + end,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartRecieveDTO>>() {}).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();

    }

    @Override
    public List<Cart> userCart(long userId) {
        List<CartRecieveDTO> cartFetchDTO = restTemplate.exchange(
                url + "/user/" + userId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartRecieveDTO>>() {}).getBody();

        assert cartFetchDTO != null;

        return cartFetchDTO.stream().map(this::mapToCart).toList();
    }

    @Override
    public void addNewCart(Cart cart) {
        CartRecieveDTO sendCart = mapToCardDTO(cart);
        sendCart = restTemplate.postForObject(url, sendCart, CartRecieveDTO.class);

//        The cart could be returned to the user as well but no need to do that right now

    }

    @Override
    public void updateProduct(Cart cart) {
        CartRecieveDTO sendCart = mapToCardDTO(cart);
        restTemplate.put(url + "/" + sendCart.date(), sendCart);
    }

    private CartRecieveDTO mapToCardDTO(Cart cart) {

        return new CartRecieveDTO(cart.id(), cart.userId(), cart.date(), cart.products());

    }

    @Override
    public void deleteCart(long id) {
        restTemplate.delete(url + "/" + id);
    }
}