package com.abhinavgpt.fakestorespring.repository;

import com.abhinavgpt.fakestorespring.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdcutRepository extends JpaRepository<Product, Long> {
}
