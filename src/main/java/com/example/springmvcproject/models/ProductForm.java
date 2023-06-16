package com.example.springmvcproject.models;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ProductForm {
    private Long id;
    @Size(min=2, max=20, message="Code must be between 2 and 20 characters")
    private String code;
    @Size(min=2, max=20, message="Name must be between 2 and 20 characters")
    private String name;
    @Positive(message= "Please set the price")
    private Double price;
    @Positive(message ="Please set the quantity")
    private Integer quantity;
    private String image = "no-image.png";
}