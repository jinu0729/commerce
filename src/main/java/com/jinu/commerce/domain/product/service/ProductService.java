package com.jinu.commerce.domain.product.service;

import com.jinu.commerce.global.dto.ResponseBodyDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseBodyDto> getAllProducts();
}
