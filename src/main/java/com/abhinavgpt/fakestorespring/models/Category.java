package com.abhinavgpt.fakestorespring.models;

public record Category(Long id, String name) {
    public Category {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }
}

