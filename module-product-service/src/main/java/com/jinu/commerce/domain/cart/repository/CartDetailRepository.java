package com.jinu.commerce.domain.cart.repository;

import com.jinu.commerce.domain.cart.entity.Cart;
import com.jinu.commerce.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDetailRepository extends JpaRepository<CartItem, Long> {
    @EntityGraph(attributePaths = "product")
    List<CartItem> findAllByCart(Cart cart);
}
