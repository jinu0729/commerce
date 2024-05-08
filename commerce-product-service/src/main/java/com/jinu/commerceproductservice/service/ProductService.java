package com.jinu.commerceproductservice.service;

import com.jinu.commerceproductservice.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product findById(Long productId);

    List<Product> getAllProductsByIdForOrderDetail(List<Long> productIds);
}