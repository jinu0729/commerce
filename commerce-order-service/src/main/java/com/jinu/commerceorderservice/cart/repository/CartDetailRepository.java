package com.jinu.commerceorderservice.cart.repository;

import com.jinu.commerceorderservice.cart.entity.Cart;
import com.jinu.commerceorderservice.cart.entity.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartItem, Long> {
    @EntityGraph(attributePaths = "product")
    List<CartItem> findAllByCart(Cart cart);
}
