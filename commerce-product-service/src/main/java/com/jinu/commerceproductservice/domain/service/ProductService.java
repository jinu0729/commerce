package com.jinu.commerceproductservice.domain.service;

import com.jinu.commerceproductservice.domain.entity.Product;
import com.jinu.commerceproductservice.domain.entity.Type;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProductByProductId(Long productId);

    List<Product> getAllProductsByIdForOrderDetail(List<Long> productIds);

    void decreaseStock(Long productId);

    void decreaseStockRedisson(Long productId);

    void increaseStock(Long productId);

    void updateIsActive(Type type, Boolean isActive);

    Product findProductByProductId(Long productId);

    List<Product> findProductByType(Type type);
}