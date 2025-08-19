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
    public List<ShopsModel> fetchShops(Long collegeName){
        return shopsRepository.findAllByCollege_CollegeId(collegeName);
    }
    public ShopsModel fetchShopById(Long shopId){
        return shopsRepository.findByShopId(shopId);
    }

    public ShopsModel updateShop(ShopsModel shop){
        return shopsRepository.save(shop);
    }
    
}
