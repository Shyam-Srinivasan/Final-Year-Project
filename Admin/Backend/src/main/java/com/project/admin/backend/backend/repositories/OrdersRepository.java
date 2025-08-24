package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersModel, Long> {
}
