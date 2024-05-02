package com.jinu.commerceproductservice.product.repository;

import com.jinu.commerceproductservice.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
