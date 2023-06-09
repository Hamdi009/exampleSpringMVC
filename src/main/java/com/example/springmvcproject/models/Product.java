package com.example.springmvcproject.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String code;
    private String name;
    private Double price;
    private Integer quantity;
    private String image;

    public Product(Long id, String code, String name, Double price, Integer quantity, String image) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = "images/"+image;;
    }


}