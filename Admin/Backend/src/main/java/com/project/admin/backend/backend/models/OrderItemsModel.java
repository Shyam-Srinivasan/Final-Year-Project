package com.project.admin.backend.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Order_Items")
public class OrderItemsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    @JsonProperty("order_item_id")
    private Long orderItemId;
    
    @Column(name = "order_id")
    @JsonProperty("order_id")
    private Long orderId;
    
    @Column(name = "item_id")
    @JsonProperty("item_id")
    private Long itemId;
    
    @Column(name = "item_name")
    @JsonProperty("item_name")
    private String itemName;
    
    @Column(name = "image_path")
    @JsonProperty("image_path")
    private String imagePath;
    
    @Column(name = "price")
    @JsonProperty("price")
    private BigDecimal price;
    
    @Column(name = "quantity")
    @JsonProperty("quantity")
    private Integer quantity;
    
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    OrdersModel order;
    
    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    ItemsModel item;
    

}
