package com.jinu.commerceproductservice.domain.service;

import com.jinu.commerceproductservice.domain.entity.Product;
import com.jinu.commerceproductservice.domain.repository.ProductRepository;
import com.jinu.commerceproductservice.global.exception.CustomException;
import com.jinu.commerceproductservice.global.exception.ErrorCode;
import com.jinu.commerceproductservice.global.redis.DistributedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j(topic = "ProductServiceImpl")
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        log.info("상품 전체조회");

        return this.productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        log.info("상품 상세조회");

        return this.findById(productId);
    }

    @Override
    public List<Product> getAllProductsByIdForOrderDetail(List<Long> productIds) {
        log.info("주문상품 전체조회");

        return this.productRepository.findAllById(productIds);
    }

    @Override
    public void updateOrderableStatus(Long productId, Boolean orderable) {

    }

    @Override
    @Transactional
    public void reduceProductStock(Long productId) {
        Product product = this.findById(productId);
        product.decrease();
    }

    @Override
    @DistributedLock(value = "#productId")
    public void reduceProductStockRedisson(Long productId) {
        Product product = this.findById(productId);
        product.decrease();
    }

    @Override
    public Product findById(Long productId) {
        return this.productRepository.findById(1L)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
    }
}