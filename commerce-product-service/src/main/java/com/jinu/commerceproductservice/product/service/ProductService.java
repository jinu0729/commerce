package com.jinu.commerceproductservice.product.service;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceproductservice.product.entity.Product;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseBodyDto> getAllProducts();

    ResponseEntity<ResponseBodyDto> getProductById(Long productId);

    Product findById(Long productId);
}
