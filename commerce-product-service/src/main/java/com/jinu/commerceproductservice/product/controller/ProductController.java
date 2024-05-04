package com.jinu.commerceproductservice.product.controller;

import com.jinu.commercecommon.dto.ResponseBodyDto;
import com.jinu.commerceproductservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<ResponseBodyDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseBodyDto> getProductByProductId(@PathVariable(name = "productId") Long productId) {
        return productService.getProductById(productId);
    }
}