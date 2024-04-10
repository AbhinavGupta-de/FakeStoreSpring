package com.abhinavgpt.fakestorespring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {

        @Id
        private long id;
        private String name;
        private String description;
        private double price;
        private String image;
        @ManyToOne
        private Category category;

        public Product() {
        }

        public Product(long id, String name, String description, double price, String image, Category category) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.image = image;
            this.category = category;
        }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
