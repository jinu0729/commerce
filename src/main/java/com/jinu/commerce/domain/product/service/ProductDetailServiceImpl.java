package com.jinu.commerce.domain.product.service;

import com.jinu.commerce.domain.product.dto.response.ProductResponseDto;
import com.jinu.commerce.domain.product.entity.ProductDetail;
import com.jinu.commerce.domain.product.repository.ProductDetailRepository;
import com.jinu.commerce.global.dto.ResponseBodyDto;
import com.jinu.commerce.global.exception.CustomException;
import com.jinu.commerce.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j(topic = "ProductDetailServiceImpl")
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository repo;
    private final ResponseBodyDto body;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseBodyDto> getProduct(Long productId) {
        log.info("상품 상세조회");

        ProductDetail productDetail = this.repo.findByProductId(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));

        ProductResponseDto responseDto = ProductResponseDto.builder()
                .title(productDetail.getTitle())
                .price(productDetail.getPrice())
                .stock(productDetail.getStock())
                .build();

        return ResponseEntity.ok(body.successWithData("상세조회 완료", responseDto));
    }
}