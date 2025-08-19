package com.project.admin.backend.backend.controllers;

import com.project.admin.backend.backend.models.CategoriesModel;
import com.project.admin.backend.backend.models.CollegesModel;
import com.project.admin.backend.backend.models.ShopsModel;
import com.project.admin.backend.backend.services.CategoriesService;
import com.project.admin.backend.backend.services.SignInService;
import com.project.admin.backend.backend.services.SignUpService;
import com.project.admin.backend.backend.services.ShopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.5:3000"})
public class Controller {
    @Autowired
    private final SignUpService signUpService;
    @Autowired
    private final SignInService signInService;
    @Autowired
    private ShopsService shopsService;
    @Autowired
    private CategoriesService categoriesService;


    public Controller(SignUpService signUpService, SignInService signInService, ShopsService shopsService) {
        this.signUpService = signUpService;
        this.signInService = signInService;
        this.shopsService = shopsService;
    }

    @PostMapping("/signUp")
    ResponseEntity<CollegesModel> signUpPage(@RequestBody CollegesModel collegesModel){
        return new ResponseEntity<>(signUpService.createOrganization(collegesModel), HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    ResponseEntity<CollegesModel> signInPage(@RequestParam String collegeName){
        if(!signInService.validateUser(collegeName)){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        CollegesModel college = signInService.getCollegeByName(collegeName);
        if(college == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(college, HttpStatus.OK);
    }
    
    @GetMapping("/shopList/fetchShop")
    ResponseEntity<List<ShopsModel>> fetchShop(@RequestParam("collegeId") Long collegeId){
        try{
            List<ShopsModel> shops= shopsService.fetchShops(collegeId);
            if(shops.isEmpty() || shops == null) {
                return new ResponseEntity<>(shops, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(shops, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/shopList/fetchShopById")
    ResponseEntity<ShopsModel> fetchShopById(@RequestParam Long shopId){
        try {
            ShopsModel shop = shopsService.fetchShopById(shopId);
            if(shop == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/shopList/updateShop")
    ResponseEntity<ShopsModel> updateShop(@RequestParam Long shopId, @RequestBody ShopsModel updatedShop) {
        try {
            if (updatedShop == null || updatedShop.getShopName() == null || updatedShop.getPassword() == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
    
            ShopsModel shop = shopsService.fetchShopById(shopId);
            if (shop == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
    
            shop.setShopName(updatedShop.getShopName());
            shop.setPassword(updatedShop.getPassword());
    
            ShopsModel saved = shopsService.updateShop(shop);
            return new ResponseEntity<>(saved, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/shopList/createShop")
    ResponseEntity<ShopsModel> createShop(@RequestBody ShopsModel shopsModel){
        return new ResponseEntity<>(shopsService.createShop(shopsModel), HttpStatus.CREATED);
    }
    
    @PostMapping("/categoryList/createCategory")
    ResponseEntity<CategoriesModel> createCategory(@RequestBody CategoriesModel category){
        return new ResponseEntity<>(categoriesService.createCategory(category), HttpStatus.CREATED);
    }
        
    @PutMapping("/categoryList/updateCategory")
    ResponseEntity<CategoriesModel> updateCategory(@RequestParam Long categoryId, @RequestBody CategoriesModel updatedCategory){
        try {
            if(updatedCategory == null || categoryId == null || updatedCategory.getCategoryName() == null){
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            } 
            CategoriesModel category = categoriesService.fetchCategory(categoryId);
            if (category == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            category.setCategoryId(categoryId);
            category.setCategoryName(updatedCategory.getCategoryName());
            
            CategoriesModel save = categoriesService.updateCategory(category);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/categoryList/fetchCategory")
    ResponseEntity<CategoriesModel> fetchCategory(@RequestParam Long categoryId){
        try{
            CategoriesModel category = categoriesService.fetchCategory(categoryId);
            if(category == null){
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(category, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/categoryList/fetchCategories")
    ResponseEntity<List<CategoriesModel>> fetchCategories(@RequestParam Long shopId){
        try {
            List<CategoriesModel> categories = categoriesService.fetchCategories(shopId);
            if(categories.isEmpty() || categories == null){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/categoryList/deleteCategory")
    ResponseEntity<CategoriesModel> deleteCategory(@RequestParam Long categoryId){
        categoriesService.deleteCategory(categoryId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
