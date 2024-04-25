package com.abhinavgpt.fakestorespring.services.cartservice;

import com.abhinavgpt.fakestorespring.exceptions.CartNotFoundException;
import com.abhinavgpt.fakestorespring.models.Cart;

import java.util.List;

public sealed interface CartService permits  CartServiceDatabaseImpl, CartServiceImpl{

    List<Cart> getAllProducts();

    Cart getCart(long id) throws CartNotFoundException;

    List<Cart> limitedCarts(long limit);

    List<Cart> sortedCarts(String order);

    List<Cart> inDateRange(String start, String end);

    List<Cart> userCart(long userId);

    Cart addNewCart(Cart cart);

    Cart updateCart(Cart cart);

    void deleteCart(long id);


}