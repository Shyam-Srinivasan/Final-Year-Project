package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.CategoriesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<CategoriesModel, String> {
    List<CategoriesModel> findAllByShop_ShopId(String shopId);
    CategoriesModel findByCategoryId(String categoryId);
}
