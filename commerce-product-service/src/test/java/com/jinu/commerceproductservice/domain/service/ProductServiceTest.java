package com.jinu.commerceproductservice.domain.service;

import com.jinu.commerceproductservice.domain.entity.Product;
import com.jinu.commerceproductservice.domain.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    private final Integer CONCURRENT_COUNT = 100;
    private Long productId = 1L;


    private void reducingTest(Consumer<Void> action) throws InterruptedException {
        Long stock = productRepository.findById(productId).orElseThrow().getStock();

        ExecutorService executorService = Executors.newFixedThreadPool(CONCURRENT_COUNT);
        CountDownLatch latch = new CountDownLatch(CONCURRENT_COUNT);

        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            executorService.submit(() -> {
                try {
                    action.accept(null);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Product product = productRepository.findById(productId).orElseThrow();
        assertEquals(stock - CONCURRENT_COUNT, product.getStock());
    }

    @Test
    @DisplayName("동시에 재고변화 100건 : 분산락 X")
    public void badReducingTest() throws Exception {
        reducingTest((_no) -> productService.decreaseStock(productId));
    }

    @Test
    @DisplayName("동시에 재고변화 100건 : 분산락 ")
    public void redissonReducingTest() throws Exception {
        reducingTest((_no) -> productService.decreaseStockRedisson(productId));
    }
}