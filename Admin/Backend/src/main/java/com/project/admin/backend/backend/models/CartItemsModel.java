package com.project.admin.backend.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Cart_Items")
public class CartItemsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id", unique = true, nullable = false)
    @JsonProperty("cart_item_id")
    private Long cartItemId;
    
    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Long userId;
    
    @Column(name = "shop_id")
    @JsonProperty("shop_id")
    private Long shopId;
    
    @Column(name = "item_id")
    @JsonProperty("item_id")
    private Long itemId;
    
    @Column(name = "quantity")
    @JsonProperty("quantity")
    private Integer quantity;
    
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UsersModel user;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    private ShopsModel shop;
    
    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    private ItemsModel item;
    
}
