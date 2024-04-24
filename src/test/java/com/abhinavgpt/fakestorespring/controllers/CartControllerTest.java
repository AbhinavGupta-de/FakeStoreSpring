package com.abhinavgpt.fakestorespring.controllers;

import com.abhinavgpt.fakestorespring.dtos.ProductCartDTO;
import com.abhinavgpt.fakestorespring.models.Cart;
import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.models.Product;
import com.abhinavgpt.fakestorespring.services.cartservice.CartService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartControllerTest {

    @InjectMocks
    CartController cartController;

    @Mock
    CartService cartService;

    @Test
    void getAll() {
        List<Cart> carts = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));
        when(cartService.getAllProducts()).thenReturn(carts);

        List<Cart> cartsR = cartController.getAll();

        assertEquals(carts, cartsR);
    }

    @Test
    void getCart() {
        Cart cart = new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"))));
        try {
            when(cartService.getCart(1)).thenReturn(cart);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cart cartR = null;
        try {
            cartR = cartController.getCart(1L);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(cart, cartR);
    }

    @Test
    void getLimitedCarts() {
        List<Cart> carts = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));
        when(cartService.limitedCarts(1L)).thenReturn(carts);

        List<Cart> cartsR = cartController.getLimitedCarts(1L);

        assertEquals(carts, cartsR);
    }

    @Test
    void getSortedCarts() {
        List<Cart> carts = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));
        when(cartService.sortedCarts("date")).thenReturn(carts);

        List<Cart> cartsR = cartController.getSortedCarts("date");

        assertEquals(carts, cartsR);
    }

    @Test
    void betweenDateRange() {
        List<Cart> carts = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));
        when(cartService.inDateRange("2021-09-01", "2021-09-02")).thenReturn(carts);

        List<Cart> cartsR = cartController.betweenDateRange("2021-09-01", "2021-09-02");

        assertEquals(carts, cartsR);
    }

    @Test
    void getUserCart() {
        List<Cart> cart = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));
        when(cartService.userCart(1L)).thenReturn(cart);

        List<Cart> cartR = cartController.getUserCart(1L);

        assertEquals(cart, cartR);
    }

    @Test
    void addNewProduct() {
        ProductCartDTO productCartDTO = new ProductCartDTO(1L, 1);
        Cart cart = new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"))));

        cartController.addNewProduct(cart);

        Mockito.verify(cartService, Mockito.times(1)).addNewCart(cart);
    }

    @Test
    void updateProduct() {
        ProductCartDTO productCartDTO = new ProductCartDTO(1L, 1);
        Cart cart = new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"))));

        cartController.updateProduct(cart);

        Mockito.verify(cartService, Mockito.times(1)).updateCart(cart);
    }

    @Test
    void deleteProduct() {

        long productId = 1L;

        cartController.deleteProduct(productId);

        Mockito.verify(cartService, Mockito.times(1)).deleteCart(productId);
    }
}