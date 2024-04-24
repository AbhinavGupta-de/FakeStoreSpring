package com.abhinavgpt.fakestorespring.services.cartservice;

import com.abhinavgpt.fakestorespring.models.Cart;
import com.abhinavgpt.fakestorespring.models.Category;
import com.abhinavgpt.fakestorespring.models.Product;
import com.abhinavgpt.fakestorespring.repository.CartRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartServiceImplTest {

    @InjectMocks
    CartServiceDatabaseImpl cartService;

    @Mock
    CartRepository cartRepository;

    @Test
    void getAllProducts() {

        List<Cart> products = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));

        when(cartRepository.findAll()).thenReturn(products);

        List<Cart> products1 = cartService.getAllProducts();

        assertEquals(products, products1);

    }

    @Test
    void getCart() {

        Cart cart = new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"))));

        try {
            when(cartRepository.findById(1L)).thenReturn(java.util.Optional.of(cart));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cart cart1 = null;
        try {
            cart1 = cartService.getCart(1);
            assertEquals(cart, cart1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(cart, cart1);
    }

    @Test
    void limitedCarts() {
        List<Cart> products = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));

        when(cartRepository.findAll()).thenReturn(products);

        List<Cart> products1 = cartService.limitedCarts(1);

        assertEquals(products, products1);
    }

    @Test
    void sortedCarts() {

        List<Cart> products = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));

        when(cartRepository.findAll()).thenReturn(products);

        List<Cart> products1 = cartService.sortedCarts("asend");

        assertEquals(products, products1);
    }

    @Test
    void inDateRange() {

        List<Cart> products = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));

        when(cartRepository.findAll()).thenReturn(products);

        List<Cart> products1 = cartService.inDateRange("2021-09-01", "2021-09-01");

        assertEquals(products, products1);
    }

    @Test
    void userCart() {

        List<Cart> products = List.of(new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1")))));

        when(cartRepository.findAll()).thenReturn(products);

        List<Cart> products1 = cartService.userCart(1);

        assertEquals(products, products1);
    }

    @Test
    void addNewCart() {

        Cart cart = new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"))));

        when(cartRepository.save(cart)).thenReturn(cart);

        Cart cartR = cartService.addNewCart(cart);

        assertEquals(cart, cartR);
    }

    @Test
    void updateCart() {

        Cart cart = new Cart(1, 1, "2021-09-01", List.of(new Product(1, "Product 1", "", 23.0, "", new Category(1, "Category 1"))));

        when(cartRepository.save(cart)).thenReturn(cart);

        Cart cartR = cartService.updateCart(cart);

        assertEquals(cart, cartR);
    }

    @Test
    void deleteCart() {

        long cartId = 1L;

        cartService.deleteCart(cartId);

        Mockito.verify(cartRepository, times(1)).deleteById(cartId);
    }
}