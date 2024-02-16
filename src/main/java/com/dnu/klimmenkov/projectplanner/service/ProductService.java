package com.dnu.klimmenkov.projectplanner.service;

import com.dnu.klimmenkov.projectplanner.entity.Product;

import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    void deleteProduct(int id);
}
