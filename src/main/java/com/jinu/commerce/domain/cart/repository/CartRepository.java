package com.jinu.commerce.domain.cart.repository;

import com.jinu.commerce.domain.cart.entity.Cart;
import com.jinu.commerce.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

    List<Cart> findAllByUser(User user);
}
