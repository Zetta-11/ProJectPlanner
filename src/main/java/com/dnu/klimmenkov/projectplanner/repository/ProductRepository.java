package com.dnu.klimmenkov.projectplanner.repository;

import com.dnu.klimmenkov.projectplanner.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
