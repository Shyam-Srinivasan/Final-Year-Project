package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.OrderItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsModel, Long> {
}
