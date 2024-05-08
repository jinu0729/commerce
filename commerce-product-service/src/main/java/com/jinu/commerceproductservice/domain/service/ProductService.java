package com.jinu.commerceproductservice.domain.service;

import com.jinu.commerceproductservice.domain.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductById(Long productId);

    Product findById(Long productId);

    List<Product> getAllProductsByIdForOrderDetail(List<Long> productIds);

    void updateOrderableStatus(Long productId, Boolean orderable);
}