package com.abhinavgpt.fakestorespring.models;

public record Product(Long id, String title, String description, double price, Category category, String imageUrl) {
}
