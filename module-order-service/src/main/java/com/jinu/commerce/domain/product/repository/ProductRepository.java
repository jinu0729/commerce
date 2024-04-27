package com.jinu.commerce.domain.product.repository;

import com.jinu.commerce.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
