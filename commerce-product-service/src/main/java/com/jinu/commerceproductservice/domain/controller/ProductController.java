package com.jinu.commerceproductservice.domain.controller;

import com.jinu.commerceproductservice.domain.dto.ProductResponseDto;
import com.jinu.commerceproductservice.domain.service.ProductService;
import com.jinu.commerceproductservice.global.dto.ResponseBodyDto;
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

    @GetMapping("/stock/{productId}")
    public ResponseEntity<ResponseBodyDto> getProductStockById(@PathVariable(name = "productId") Long productId) {
        ProductResponseDto responseDto =
                ProductResponseDto.builder()
                        .stock(this.productService.getProductById(productId).getStock())
                        .build();

        return ResponseEntity.ok(ResponseBodyDto.successWithResult("재고조회 완료", responseDto));
    }

    @PostMapping()
    List<ProductResponseDto> getProductsByIdForOrderDetails(@RequestBody List<Long> productIds) {
        return this.productService.getAllProductsByIdForOrderDetail(productIds).stream()
                .map(ProductResponseDto::createProductResponseDtoIntoProduct)
                .toList();
    }

    @PatchMapping("/internal/decrease-stock/{productId}")
    public void decreaseStock(@PathVariable(name = "productId") Long productId) {
        this.productService.decreaseStock(productId);
    }

    @PatchMapping("/internal/increase-stock/{productId}")
    public void increaseStock(@PathVariable(name = "productId") Long productId) {
        this.productService.increaseStock(productId);
    }
}