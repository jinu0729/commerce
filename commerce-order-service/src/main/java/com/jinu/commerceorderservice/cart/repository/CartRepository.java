package com.jinu.commerceorderservice.cart.repository;

import com.jinu.commerceorderservice.cart.entity.Cart;
import com.jinu.commerceuserservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);

    List<Cart> findAllByUser(User user);
}
