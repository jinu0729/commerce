package com.jinu.commerce.domain.product.service;

import com.jinu.commerce.domain.product.dto.response.AllProductsResponseDto;
import com.jinu.commerce.domain.product.repository.ProductRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j(topic = "ProductServiceImpl")
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ResponseBodyDto body;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseBodyDto> getAllProducts() {
        log.info("상품 전체조회");

        List<AllProductsResponseDto> responseDtoList = this.productRepository.findAll().stream()
                .map(product -> AllProductsResponseDto.builder()
                        .productId(product.getProductId())
                        .title(product.getProductDetail().getTitle())
                        .price(product.getProductDetail().getPrice())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(body.successWithData("전체조회 완료", responseDtoList));
    }
}