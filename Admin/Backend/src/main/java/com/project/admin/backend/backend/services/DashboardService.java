package com.project.admin.backend.backend.services;

import com.project.admin.backend.backend.models.OrdersModel;
import com.project.admin.backend.backend.models.ShopsModel;
import com.project.admin.backend.backend.repositories.OrdersRepository;
import com.project.admin.backend.backend.repositories.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private OrdersRepository ordersRepository;
    
    public Long fetchTotalOrdersByShopId(Long shopId){
        return ordersRepository.countAllByShopId(shopId);
    }

    public Long fetchTotalOrdersByCollegeId(Long collegeId){
        return ordersRepository.countAllByShop_College_CollegeId(collegeId);
    }
    
    public Long fetchPendingOrdersByShopId(Long shopId){
        return ordersRepository.countAllByShopIdAndIsPurchased(shopId, false);
    }
    
    public Long fetchPendingOrdersByCollegeId(Long collegeId){
        return ordersRepository.countAllByShop_College_CollegeIdAndIsPurchased(collegeId, false);
    }
    
    public Long fetchCompletedOrdersByShopId(Long shopId){
        return ordersRepository.countAllByShopIdAndIsPurchased(shopId, true);
    }
    
    public Long fetchCompletedOrdersByCollegeId(Long collegeId){
        return ordersRepository.countAllByShop_College_CollegeIdAndIsPurchased(collegeId, true);
    }
}
