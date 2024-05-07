package com.jinu.commerceproductservice.domain.controller;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceproductservice.domain.dto.ProductResponseDto;
import com.jinu.commerceproductservice.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllProducts() {
        List<ProductResponseDto> responseDtoList = this.productService.getAllProducts().stream()
                .map(ProductResponseDto::createProductResponseDtoIntoProduct)
                .toList();

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("전체조회 완료", responseDtoList));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseBodyDto> getProductById(@PathVariable(name = "productId") Long productId) {
        ProductResponseDto responseDto =
                ProductResponseDto.createProductResponseDtoIntoProduct(this.productService.getProductById(productId));

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("상세조회 완료", responseDto));
    }

    @PostMapping()
    List<ProductResponseDto> getProductsByIdForOrderDetails(@RequestBody List<Long> productIds) {
        return this.productService.getAllProductsByIdForOrderDetail(productIds).stream()
                .map(ProductResponseDto::createProductResponseDtoIntoProduct)
                .toList();
    }
}