package com.jinu.commerceproductservice.domain.repository;

import com.jinu.commerceproductservice.domain.entity.Product;
import com.jinu.commerceproductservice.domain.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByType(Type type);
}