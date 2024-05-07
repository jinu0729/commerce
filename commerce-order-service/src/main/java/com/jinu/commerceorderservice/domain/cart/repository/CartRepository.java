package com.jinu.commerceorderservice.domain.cart.repository;

import com.jinu.commerceorderservice.domain.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long userId);

    List<Cart> findAllByUserId(Long userId);
}
