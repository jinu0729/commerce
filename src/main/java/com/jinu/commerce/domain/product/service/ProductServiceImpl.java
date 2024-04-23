package com.jinu.commerce.domain.product.service;

import com.jinu.commerce.domain.product.dto.response.ProductDetailResponseDto;
import com.jinu.commerce.domain.product.dto.response.ProductResponseDto;
import com.jinu.commerce.domain.product.entity.Product;
import com.jinu.commerce.domain.product.repository.ProductRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
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
    private final ResponseBodyDto responseBodyDto;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseBodyDto> getAllProducts() {
        log.info("상품 전체조회");

        List<ProductResponseDto> responseDtoList = this.productRepository.findAll().stream()
                .map(product -> ProductResponseDto.builder()
                        .productId(product.getProductId())
                        .productDetails(ProductDetailResponseDto.builder()
                                .productDetailId(product.getProductDetail().getProductDetailId())
                                .title(product.getProductDetail().getTitle())
                                .price(product.getProductDetail().getPrice())
                                .stock(product.getProductDetail().getStock())
                                .build())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseBodyDto.successWithData("전체조회 완료", responseDtoList));
    }

    @Override
    public ResponseEntity<ResponseBodyDto> getProductByProductId(Long productId) {
        log.info("상품 상세조회");

        Product product = this.findById(productId);

        ProductResponseDto responseDto = ProductResponseDto.builder()
                .productId(product.getProductId())
                .productDetails(ProductDetailResponseDto.builder()
                        .productDetailId(product.getProductDetail().getProductDetailId())
                        .title(product.getProductDetail().getTitle())
                        .price(product.getProductDetail().getPrice())
                        .stock(product.getProductDetail().getStock())
                        .build())
                .build();

        return ResponseEntity.ok(responseBodyDto.successWithData("상세조회 완료", responseDto));
    }

    @Override
    public Product findById(Long productId) {
        return this.productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
    }
}