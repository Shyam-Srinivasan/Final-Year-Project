package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.ShopsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopsRepository extends JpaRepository<ShopsModel, Long> {
    List<ShopsModel> findAllByCollege_CollegeId(Long collegeName);

    ShopsModel findByShopId(Long shop_id);
}
