package com.abhinavgpt.fakestorespring.repository;

import com.abhinavgpt.fakestorespring.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
