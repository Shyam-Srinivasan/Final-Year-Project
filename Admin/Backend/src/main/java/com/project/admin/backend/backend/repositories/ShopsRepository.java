package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.ShopsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopsRepository extends JpaRepository<ShopsModel, String> {
    List<ShopsModel> findAllByCollege_CollegeId(String college_id);

    ShopsModel findByShopId(String shop_id);
}
