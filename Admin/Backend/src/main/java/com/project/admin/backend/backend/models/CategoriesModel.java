package com.project.admin.backend.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Categories")
public class CategoriesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id")
    @JsonProperty("category_id")
    private String categoryId;
    
    @Column(name = "shop_id")
    @JsonProperty("shop_id")
    private String shopId;
    
    @Column(name = "category_name")
    @JsonProperty("category_name")
    private String categoryName;
    
    @Column(name = "image_path")
    @JsonProperty("image_path")
    private String imagePath;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    @JsonProperty("shop")
    private ShopsModel shop;
}
