package com.jinu.commerceorderservice.order.repository;

import com.jinu.commerceorderservice.order.entity.Order;
import com.jinu.commerceorderservice.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @EntityGraph(attributePaths = "product")
    List<OrderDetail> findAllByOrder(Order order);

    void deleteAllByOrder(Order order);
}
