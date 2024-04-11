package com.abhinavgpt.fakestorespring.dtos;


import java.util.List;

public record CartReceiveDTO(long id, long userId, String date, List<ProductCartDTO> products) {

}