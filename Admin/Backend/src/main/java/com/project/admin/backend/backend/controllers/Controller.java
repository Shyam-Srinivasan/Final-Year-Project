package com.project.admin.backend.backend.controllers;

import com.project.admin.backend.backend.OrderResponse;
import com.project.admin.backend.backend.models.*;
import com.project.admin.backend.backend.repositories.OrderItemsRepository;
import com.project.admin.backend.backend.repositories.UsersRepository;
import com.project.admin.backend.backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.1:3000", "http://192.168.1.2:3000", "http://192.168.1.3:3000", "http://192.168.1.4:3000", "http://192.168.1.5:3000", "http://192.168.1.6:3000", "http://192.168.1.7:3000", "http://192.168.1.8:3000", "http://192.168.1.9:3000"})
public class Controller {
    @Autowired
    private final SignUpService signUpService;
    @Autowired
    private final SignInService signInService;
    @Autowired
    private ShopsService shopsService;
    @Autowired
    private CategoriesService categoriesService;
    @Autowired
    private ItemsService itemsService;
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private OrderItemsRepository orderItemsRepository;


    public Controller(SignUpService signUpService, SignInService signInService, ShopsService shopsService) {
        this.signUpService = signUpService;
        this.signInService = signInService;
        this.shopsService = shopsService;
    }

    @PostMapping("/signUp")
    ResponseEntity<CollegesModel> signUpPage(@RequestBody CollegesModel collegesModel) {
        return new ResponseEntity<>(signUpService.createOrganization(collegesModel), HttpStatus.CREATED);
    }

    @GetMapping("/signIn")
    ResponseEntity<CollegesModel> signInPage(@RequestParam String collegeName) {
        if (!signInService.validateUser(collegeName)) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        CollegesModel college = signInService.getCollegeByName(collegeName);
        if (college == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(college, HttpStatus.OK);
    }

    @GetMapping("/shopList/fetchShop")
    ResponseEntity<List<ShopsModel>> fetchShop(@RequestParam("collegeId") Long collegeId) {
        try {
            List<ShopsModel> shops = shopsService.fetchShops(collegeId);
            if (shops.isEmpty() || shops == null) {
                return new ResponseEntity<>(shops, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(shops, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/shopList/fetchShopById")
    ResponseEntity<ShopsModel> fetchShopById(@RequestParam Long shopId) {
        try {
            ShopsModel shop = shopsService.fetchShopById(shopId);
            if (shop == null) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
    ResponseEntity<ShopsModel> createShop(@RequestBody ShopsModel shopsModel) {
        return new ResponseEntity<>(shopsService.createShop(shopsModel), HttpStatus.CREATED);
    }

    @PostMapping("/categoryList/createCategory")
    ResponseEntity<CategoriesModel> createCategory(@RequestBody CategoriesModel category) {
        return new ResponseEntity<>(categoriesService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/categoryList/updateCategory")
    ResponseEntity<CategoriesModel> updateCategory(@RequestParam Long categoryId, @RequestBody CategoriesModel updatedCategory) {
        try {
            if (updatedCategory == null || categoryId == null || updatedCategory.getCategoryName() == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
            CategoriesModel category = categoriesService.fetchCategory(categoryId);
            if (category == null) {
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
    ResponseEntity<CategoriesModel> fetchCategory(@RequestParam Long categoryId) {
        try {
            CategoriesModel category = categoriesService.fetchCategory(categoryId);
            if (category == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(category, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoryList/fetchCategories")
    ResponseEntity<List<CategoriesModel>> fetchCategories(@RequestParam Long shopId) {
        try {
            List<CategoriesModel> categories = categoriesService.fetchCategories(shopId);
            if (categories.isEmpty() || categories == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/categoryList/deleteCategory")
    ResponseEntity<CategoriesModel> deleteCategory(@RequestParam Long categoryId) {
        categoriesService.deleteCategory(categoryId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/itemList/createItem")
    ResponseEntity<ItemsModel> createItem(@RequestBody ItemsModel item) {
        return new ResponseEntity<>(itemsService.createItem(item), HttpStatus.CREATED);
    }

    @PutMapping("/itemList/updateItem")
    ResponseEntity<ItemsModel> updateItem(@RequestParam Long itemId, @RequestBody ItemsModel updatedItem) {
        try {
            if (updatedItem == null || itemId == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            ItemsModel item = itemsService.fetchItem(itemId);

            if (item == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            item.setItemName(updatedItem.getItemName());
            item.setCategoryId(updatedItem.getCategoryId());
            item.setImagePath(updatedItem.getImagePath());
            item.setStockQuantity(updatedItem.getStockQuantity());

            ItemsModel save = itemsService.updateItem(item);
            return new ResponseEntity<>(save, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/itemList/fetchItem")
    ResponseEntity<ItemsModel> fetchItem(@RequestParam Long itemId) {
        try {
            ItemsModel item = itemsService.fetchItem(itemId);
            if (item == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(item, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/itemList/fetchItems")
    ResponseEntity<List<ItemsModel>> fetchItems(@RequestParam Long categoryId) {
        try {
            List<ItemsModel> items = itemsService.fetchItems(categoryId);
            if (items.isEmpty() || items == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/itemList/deleteItem")
    ResponseEntity<ItemsModel> deleteItem(@RequestParam Long categoryId) {
        try {
            if (itemsService.deleteItem(categoryId)) {
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/itemList/updateStockQuantity")
    ResponseEntity<ItemsModel> updateStockQuantityByItemId(@RequestParam Long itemId, @RequestParam Integer stockQuantity) {
        try {
            itemsService.updateStockQuantityByItemId(itemId, stockQuantity);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/home/orders-shop-id")
    Long getTotalOrdersByShopId(@RequestParam Long shopId) {
        return dashboardService.fetchTotalOrdersByShopId(shopId);
    }

    @GetMapping("/home/orders-college-id")
    Long getTotalOrdersByCollegeId(@RequestParam Long collegeId) {
        return dashboardService.fetchTotalOrdersByCollegeId(collegeId);
    }

    @GetMapping("/home/pending-shop-id")
    Long getPendingOrdersByShopId(@RequestParam Long shopId) {
        return dashboardService.fetchPendingOrdersByShopId(shopId);
    }

    @GetMapping("/home/pending-college-id")
    Long getPendingOrdersByCollegeId(@RequestParam Long collegeId) {
        return dashboardService.fetchPendingOrdersByCollegeId(collegeId);
    }

    @GetMapping("/home/completed-shop-id")
    Long getCompletedOrdersByShopId(@RequestParam Long shopId) {
        return dashboardService.fetchCompletedOrdersByShopId(shopId);
    }

    @GetMapping("/home/completed-college-id")
    Long getCompletedOrdersByCollegeId(@RequestParam Long collegeId) {
        return dashboardService.fetchCompletedOrdersByCollegeId(collegeId);
    }
    
//    @GetMapping("/home/recent-orders-process")
    public List<OrdersModel> fetchRecentOrdersProcess(Long collegeId) {
        return dashboardService.fetchRecentOrders(collegeId);
    }
    
    
    @GetMapping("/home/recent-orders")
    public ResponseEntity<List<OrderResponse>> fetchRecentOrders(@RequestParam Long collegeId){
        List<OrdersModel> orders = fetchRecentOrdersProcess(collegeId);
        
        List<OrderResponse> response = orders.stream().map(order -> {
            List<OrderItemsModel> items = orderItemsRepository.findAllByOrderId(order.getOrderId());
            
            List<String> itemNames = items.stream().map(OrderItemsModel::getItemName).toList();
            
            List<Integer> quantities = items.stream().map(OrderItemsModel::getQuantity).toList();
            return new OrderResponse(
                    order.getOrderId(),
                    order.getUser().getUserId(),
                    order.getUser().getUserName(),
                    order.getShop().getShopName(),
                    order.getTotalAmount(),
                    order.getIsPurchased(),
                    order.getTimeStamp(),
                    itemNames,
                    quantities
            );
        }).toList();
        
        return ResponseEntity.ok(response);
        
    }
}
