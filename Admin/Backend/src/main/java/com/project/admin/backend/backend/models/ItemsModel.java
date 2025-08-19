package com.project.admin.backend.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "Items")
public class ItemsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    @JsonProperty("item_id")
    private Long itemId;
    
    @Column(name = "category_id")
    @JsonProperty("category_id")
    private Long categoryId;
    
    @Column(name = "item_name")
    @JsonProperty("item_name")
    private String itemName;
    
    @Column(name = "image_path")
    @JsonProperty("image_path")
    private String imagePath;
    
    @Column(name = "price", precision = 10, scale = 2)
    @JsonProperty("price")
    private BigDecimal price;
    
    @Column(name = "stock_quantity")
    @JsonProperty("stock_quantity")
    private Integer stockQuantity;
    
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    @JsonProperty("category")
    private CategoriesModel category;

}
