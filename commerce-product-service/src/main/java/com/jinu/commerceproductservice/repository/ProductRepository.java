package com.jinu.commerceproductservice.repository;

import com.jinu.commerceproductservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
