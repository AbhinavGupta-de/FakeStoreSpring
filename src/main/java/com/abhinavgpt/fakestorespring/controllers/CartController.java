package com.abhinavgpt.fakestorespring.controllers;

import com.abhinavgpt.fakestorespring.exceptions.CartNotFoundException;
import com.abhinavgpt.fakestorespring.models.Cart;
import com.abhinavgpt.fakestorespring.services.cartservice.CartService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("")
    public List<Cart> getAll() {
        return cartService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable("id") Long id) throws CartNotFoundException {
        return cartService.getCart(id);
    }

    @GetMapping("/limited/{limit}")
    public List<Cart> getLimitedCarts(@PathVariable("limit") Long limit) {
        return cartService.limitedCarts(limit);
    }

    @GetMapping("/sorted")
    public List<Cart> getSortedCarts(@PathParam("order") String order) {
        return cartService.sortedCarts(order);
    }

    @GetMapping("/dateRange")
    public List<Cart> betweenDateRange(@RequestParam("from") String start, @RequestParam("to") String end) {
        return cartService.inDateRange(start, end);
    }

    @GetMapping("/user/{userId}")
    public List<Cart> getUserCart(@PathVariable("userId") Long userId) {
        return cartService.userCart(userId);
    }

    @PostMapping("")
    public String addNewProduct(@RequestBody Cart cart) {

        try {
            cartService.addNewCart(cart);
        } catch (Exception e) {
            return "There was an error while adding new cart";
        }

        return "Added new product successfully";

    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody Cart cart) {

        try {
            cartService.updateCart(cart);
        } catch (Exception e) {
            return "There was an error while updating the product";
        }

        return "Product updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {

        try {
            cartService.deleteCart(id);
        } catch (Exception e) {
            return "There was an error while deleting the cart";
        }

        return "Product deleted successful";
    }

}