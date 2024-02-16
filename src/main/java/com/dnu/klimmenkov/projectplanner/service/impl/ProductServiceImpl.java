package com.dnu.klimmenkov.projectplanner.service.impl;

import com.dnu.klimmenkov.projectplanner.entity.Product;
import com.dnu.klimmenkov.projectplanner.repository.ProductRepository;
import com.dnu.klimmenkov.projectplanner.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        productRepository.save(product);
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getReferenceById((long) id);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById((long) id);
    }
}
