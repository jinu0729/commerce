package com.jinu.commerceproductservice.domain.component;

import com.jinu.commerceproductservice.domain.entity.Type;
import com.jinu.commerceproductservice.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j(topic = "ProductScheduler")
@Component
@RequiredArgsConstructor
public class ProductScheduler {
    private final ProductService productService;

    @Scheduled(cron = "0 0 14 * * ?")
    public void activeLimitedProducts() {
        log.info("매일 14시 한정구매상품 활성화");

        this.productService.updateIsActive(Type.PREORDER, true);
    }

    @Scheduled(cron = "0 30 14 * * ?")
    public void disableLimitedProducts() {
        log.info("매일 14시 30분 한정구매상품 비활성화");

        this.productService.updateIsActive(Type.PREORDER, false);
    }
}