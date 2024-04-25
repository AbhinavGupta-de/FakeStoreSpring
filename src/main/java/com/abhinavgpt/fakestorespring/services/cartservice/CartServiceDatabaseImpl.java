package com.abhinavgpt.fakestorespring.services.cartservice;

import com.abhinavgpt.fakestorespring.exceptions.CartNotFoundException;
import com.abhinavgpt.fakestorespring.models.Cart;
import com.abhinavgpt.fakestorespring.repository.CartRepository;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
final class CartServiceDatabaseImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceDatabaseImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Cart> getAllProducts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCart(long id) throws CartNotFoundException {
        return cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("Cart with id " + id + " not found"));
    }

    @Override
    public List<Cart> limitedCarts(long limit) {
        return cartRepository.findAll().subList(0, (int) limit);
    }

    @Override
    public List<Cart> sortedCarts(String order) {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> inDateRange(String start, String end) {
        return cartRepository.findAll();
    }

    @Override
    public List<Cart> userCart(long userId) {
        return cartRepository.findAll().stream().filter(cart -> cart.getUserId() == userId).toList();
    }

    @Override
    public Cart addNewCart(Cart cart) {

        try {
            return cartRepository.save(cart);
        } catch (Exception e) {
            throw new RuntimeException("Error while adding new cart");
        }
    }

    @Override
    public Cart updateCart(Cart cart) {

        try {
            return cartRepository.save(cart);
        } catch (Exception e) {
            throw new RuntimeException("Error while updating cart");
        }

    }

    @Override
    public void deleteCart(long id) {

        try {
            cartRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting cart");
        }

    }
}
