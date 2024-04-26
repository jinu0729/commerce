package com.jinu.commerce.domain.cart.repository;

import com.jinu.commerce.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartItem, Long> {
/*    @EntityGraph(attributePaths = "product")
    List<CartItem> findAllByOrder(Cart cart);

    void deleteAllByOrder(Cart cart);*/
}
