package com.jinu.commerceproductservice.global.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {
    // Lock의 이름 (고유값)
    String value();

    // Lock획득을 시도하는 최대 시간 (ms)
    long waitTime() default 5000L;

    // 락을 획득한 후, 점유하는 최대 시간 (ms)
    long leaseTime() default 2000L;
}
