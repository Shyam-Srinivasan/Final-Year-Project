package com.project.admin.backend.backend.services;

import com.project.admin.backend.backend.models.ShopsModel;
import com.project.admin.backend.backend.repositories.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopsService {
    @Autowired
    private ShopsRepository shopsRepository;
    
    public ShopsModel createShop(ShopsModel shopsModel){
        return shopsRepository.save(shopsModel);
    }
    public List<ShopsModel> fetchShops(String college_id){
        return shopsRepository.findAllByCollege_CollegeId(college_id);
    }
    public ShopsModel fetchShopById(String shop_id){
        return shopsRepository.findByShopId(shop_id);
    }

    public ShopsModel updateShop(ShopsModel shop){
        return shopsRepository.save(shop);
    }
    
}
