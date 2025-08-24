package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.ItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemsRepository extends JpaRepository<ItemsModel, Long> {
    List<ItemsModel> findAllByCategory_CategoryId(Long CategoryId);

    ItemsModel findByItemId(Long itemId);

    @Modifying
    @Transactional
    @Query("UPDATE ItemsModel i SET i.stockQuantity = :stockQuantity WHERE i.itemId = :itemId")
    int updateStockQuantityByItemId(@Param("itemId") Long itemId, @Param("stockQuantity") Integer stockQuantity);
}
