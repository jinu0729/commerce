package com.jinu.commerceproductservice.product.service;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commercecommon.exception.CustomException;
import com.jinu.commercecommon.exception.ErrorCode;
import com.jinu.commerceproductservice.product.dto.response.ProductResponseDto;
import com.jinu.commerceproductservice.product.entity.Product;
import com.jinu.commerceproductservice.product.repository.ProductRepository;
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseBodyDto> getAllProducts() {
        log.info("상품 전체조회");

        List<ProductResponseDto> responseDtoList = this.productRepository.findAll().stream()
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getProductId())
                        .title(product.getTitle())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("전체조회 완료", responseDtoList));
    }

    @Override
    public ResponseEntity<ResponseBodyDto> getProductById(Long productId) {
        log.info("상품 상세조회");

        Product product = this.findById(productId);

        ProductResponseDto responseDto = ProductResponseDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("상세조회 완료", responseDto));
    }

    @Override
    public Product findById(Long productId) {
        return this.productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
    }
}