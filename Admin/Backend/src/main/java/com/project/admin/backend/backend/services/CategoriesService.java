package com.project.admin.backend.backend.services;

import com.project.admin.backend.backend.models.CategoriesModel;
import com.project.admin.backend.backend.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    
    public List<CategoriesModel> fetchCategories(Long shopId){
        return categoriesRepository.findAllByShop_ShopId(shopId);
    }
    
    public CategoriesModel fetchCategory(Long categoryId){
        return categoriesRepository.findByCategoryId(categoryId);
    }
    
    public CategoriesModel createCategory(CategoriesModel category){
        return categoriesRepository.save(category);
    }

    public CategoriesModel updateCategory(CategoriesModel category){
        return categoriesRepository.save(category);
    }
    
    public void deleteCategory(Long categoryId){
        categoriesRepository.deleteById(categoryId);
    }
}
