package com.jinu.commerce.domain.order.repository;

import com.jinu.commerce.domain.order.entity.Order;
import com.jinu.commerce.domain.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @EntityGraph(attributePaths = "product")
    List<OrderDetail> findAllByOrder(Order order);

    void deleteAllByOrder(Order order);
}
