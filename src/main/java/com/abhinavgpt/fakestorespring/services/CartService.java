package com.abhinavgpt.fakestorespring.services;
import com.abhinavgpt.fakestorespring.models.Cart;

import java.util.List;

public interface CartService {

    List<Cart> getAllProducts();

    Cart getCart(long id);

    List<Cart> limitedCarts(long limit);

    List<Cart> sortedCarts(String order);

    List<Cart> inDateRange(String start, String end);

    List<Cart> userCart(long userId);

    void addNewCart(Cart cart);

    void updateProduct(Cart cart);

    void deleteCart(long id);


}