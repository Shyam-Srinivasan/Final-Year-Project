package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.ItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsRepository extends JpaRepository<ItemsModel, Long> {
    List<ItemsModel> findAllByCategory_CategoryId(Long CategoryId);

    ItemsModel findByItemId(Long itemId);
}
