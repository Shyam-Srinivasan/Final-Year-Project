package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.OrderItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, Long> {
    List<OrderItemsModel> findAllByOrderId(Long orderId);
}
