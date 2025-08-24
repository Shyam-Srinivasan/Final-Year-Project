package com.project.admin.backend.backend.repositories;

import com.project.admin.backend.backend.models.CollegesModel;
import com.project.admin.backend.backend.models.OrdersModel;
import com.project.admin.backend.backend.models.ShopsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersModel, Long> {

    Long countAllByShopId(Long shopId);
    Long countAllByShopIdAndIsPurchased(Long shopId, Boolean isPurchased);

    Long countAllByShop_College_CollegeId(Long collegeId);

    Long countAllByShop_College_CollegeIdAndIsPurchased(Long collegeId, Boolean isPurchased);
}
