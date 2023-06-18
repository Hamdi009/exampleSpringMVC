package com.example.springmvcproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.example.springmvcproject.models.Product;
import com.example.springmvcproject.repositories.ProductRepository;

@Service
public class productServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new NotFoundException("null"));
    }

    @Override
    public Product addProduct(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product updateProduct(Product p) {
        
        return productRepository.save(p);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> gettAllProduct() {
       return productRepository.findAll();
    }
    
}