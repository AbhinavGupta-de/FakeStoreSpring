package com.abhinavgpt.fakestorespring.dtos;


import com.abhinavgpt.fakestorespring.models.Product;

import java.util.List;

public record CartRecieveDTO(long id, long userId, String date, List<Product> products) {

}