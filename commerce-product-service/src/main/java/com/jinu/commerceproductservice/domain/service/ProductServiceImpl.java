package com.jinu.commerceproductservice.domain.service;

import com.jinu.commerceproductservice.domain.entity.Product;
import com.jinu.commerceproductservice.domain.entity.Type;
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
    @Transactional(readOnly = true)
    public Product getProductByProductId(Long productId) {
        log.info("상품 상세조회");

        return this.findProductByProductId(productId);
    }

    @Override
    public List<Product> getAllProductsByIdForOrderDetail(List<Long> productIds) {
        log.info("주문상품 전체조회");

        return this.productRepository.findAllById(productIds);
    }

    @Override
    @Transactional
    public void decreaseStock(Long productId) {
        log.info("상품 재고 감소");

        Product product = this.findProductByProductId(productId);
        product.decreaseStock();
    }

    @Override
    @DistributedLock(value = "#productId")
    public void decreaseStockRedisson(Long productId) {
        log.info("상품 재고 감소");

        Product product = this.findProductByProductId(productId);
        product.decreaseStock();
    }

    @Override
    @DistributedLock(value = "#productId")
    public void increaseStock(Long productId) {
        log.info("상품 재고 증가");

        Product product = this.findProductByProductId(productId);
        product.increaseStock();
    }

    @Override
    @Transactional
    public void updateIsActive(Type type, Boolean isActive) {
        log.info("상품 활성화 업데이트");

        List<Product> products = this.findProductByType(type);
        products.forEach(product -> product.updateIsActiveStatus(isActive));
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductByProductId(Long productId) {
        log.info("productId 로 상품조회");

        return this.productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_PRODUCT));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductByType(Type type) {
        log.info("type으로 상품조회");

        return this.productRepository.findByType(type);
    }
}