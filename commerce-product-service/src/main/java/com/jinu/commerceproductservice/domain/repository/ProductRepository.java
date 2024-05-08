package com.jinu.commerceproductservice.domain.repository;

import com.jinu.commerceproductservice.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
