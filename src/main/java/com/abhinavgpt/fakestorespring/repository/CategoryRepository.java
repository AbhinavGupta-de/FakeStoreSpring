package com.abhinavgpt.fakestorespring.repository;

import com.abhinavgpt.fakestorespring.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
