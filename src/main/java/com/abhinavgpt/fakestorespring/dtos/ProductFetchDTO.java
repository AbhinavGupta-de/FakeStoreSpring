package com.abhinavgpt.fakestorespring.dtos;

import com.abhinavgpt.fakestorespring.models.Category;

public record ProductFetchDTO(Long id, String title, String description, double price, String category, String image) {
}
