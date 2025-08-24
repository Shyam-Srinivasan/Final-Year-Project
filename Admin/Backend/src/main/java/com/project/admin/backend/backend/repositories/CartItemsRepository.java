package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.CartItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemsRepository extends JpaRepository<CartItemsModel, Long> {
}
