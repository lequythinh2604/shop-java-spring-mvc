package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> handleFindAll() {
        return productRepository.findAll();
    }

    public Product handleSave(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findOneById(Long id) {
        return productRepository.findById(id);
    }

    public void handleDelete(Long id) {
        productRepository.deleteById(id);
    }

}
