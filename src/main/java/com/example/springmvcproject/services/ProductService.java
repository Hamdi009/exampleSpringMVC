package com.example.springmvcproject.services;

import java.util.List;
import java.util.Optional;

import com.example.springmvcproject.models.Product;

public interface ProductService {
    public Optional<Product> getProduct(Long id);
    public Product addProduct(Product p);
    public Product updateProduct(long id, Product p);
    public void deleteProduct(Long id);
    public List<Product> gettAllProduct();
}