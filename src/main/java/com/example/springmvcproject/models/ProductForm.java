package com.example.springmvcproject.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductForm {
    private Long id;
    @NotBlank(message = "")
    private String code;
    @NotBlank(message = "")
    private String name;
    @NotNull(message="")
    @DecimalMin(value="")
    private Double price;
    @NotNull(message="")
    private Integer quantity;
    private String image;
}