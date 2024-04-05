package com.abhinavgpt.fakestorespring.models;

//private Long id;
//private String title;
//private String description;
//private double price;
//private Category category;
//private String imageUrl;

public record Product(Long id, String title, String description, double price, Category category, String imageUrl) {
}
