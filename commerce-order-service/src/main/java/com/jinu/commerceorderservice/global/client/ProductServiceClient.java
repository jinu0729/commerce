package com.jinu.commerceorderservice.global.client;

import com.jinu.commerceorderservice.global.client.dto.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "commerce-product-service")
public interface ProductServiceClient {

    @PostMapping("/api/product")
    List<ProductResponseDto> getProductsByIdForOrderDetails(@RequestBody List<Long> productIds);

    @PatchMapping("/api/product/internal/decrease-stock/{productId}")
    void decreaseStock(@PathVariable(name = "productId") Long productId);

    @PatchMapping("/api/product/internal/increase-stock/{productId}")
    void increaseStock(@PathVariable(name = "productId") Long productId);
}