package com.example.springmvcproject.models;

import lombok.Data;

@Data
public class Product {
    private long id;
    private String code;
    private String name;
    private Double price;
    private Integer quantity;
    private String image;

}