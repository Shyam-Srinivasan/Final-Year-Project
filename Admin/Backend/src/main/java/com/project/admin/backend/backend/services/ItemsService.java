package com.project.admin.backend.backend.services;

import com.project.admin.backend.backend.models.ItemsModel;
import com.project.admin.backend.backend.repositories.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
    @Autowired
    private ItemsRepository itemsRepository;
    
    public ItemsModel createItem(ItemsModel itemsModel){
        return itemsRepository.save(itemsModel);
    }
    
    public ItemsModel updateItem(ItemsModel itemsModel){
        return itemsRepository.save(itemsModel);
    }
    
    public List<ItemsModel> fetchItems(Long categoryId){
        return itemsRepository.findAllByCategory_CategoryId(categoryId);
    }
    
    public ItemsModel fetchItem(Long itemId){
        return itemsRepository.findByItemId(itemId);
    }
    
    public boolean deleteItem(Long itemId){
        try{
            itemsRepository.deleteById(itemId);
            return true;
        } catch (Exception e){
            return false;
        }
        
    }
    
    public void updateStockQuantityByItemId(Long itemId, Integer stockQuantity){
        int updatedRows =  itemsRepository.updateStockQuantityByItemId(itemId, stockQuantity);
        if(updatedRows == 0){
            throw new RuntimeException("Item not found" + itemId);      
        }
    }
    
}


