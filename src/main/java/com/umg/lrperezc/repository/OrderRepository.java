package com.umg.lrperezc.repository;

import com.umg.lrperezc.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByClient_IdAndStatusIgnoreCase(int clientId, String status);
}
