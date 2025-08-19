package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoriesModel, Long> {
    List<CategoriesModel> findAllByShop_ShopId(Long shopId);
    CategoriesModel findByCategoryId(Long categoryId);
}
