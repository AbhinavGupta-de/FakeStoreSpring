package com.abhinavgpt.fakestorespring.dtos;

public record ProductFetchDTO(Long id, String title, String description, double price, String category, String image) {
}
