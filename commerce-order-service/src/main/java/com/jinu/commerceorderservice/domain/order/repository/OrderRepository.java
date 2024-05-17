package com.jinu.commerceorderservice.domain.order.repository;

import com.jinu.commerceorderservice.domain.order.entity.Order;
import com.jinu.commerceorderservice.domain.order.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.createdAt < :tenMinutesAgo")
    List<Order> findOrdersByStatusAndCreatedAtBeforeGivenTime(Status status, LocalDateTime tenMinutesAgo);
}
