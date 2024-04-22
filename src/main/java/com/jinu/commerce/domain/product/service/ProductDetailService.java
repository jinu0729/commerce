package com.jinu.commerce.domain.product.service;

import com.jinu.commerce.global.dto.ResponseBodyDto;
import org.springframework.http.ResponseEntity;

public interface ProductDetailService {
    ResponseEntity<ResponseBodyDto> getProduct(Long productId);
}
