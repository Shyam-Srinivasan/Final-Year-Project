package com.project.admin.backend.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Orders")
public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    @JsonProperty("order_id")
    private Long orderId;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Long userId;

    @Column(name = "shop_id")
    @JsonProperty("shop_id")
    private Long shopId;

    @Column(name = "is_purchased")
    @JsonProperty("is_purchased")
    private Boolean isPurchased;

    @Column(name = "time_stamp")
    @JsonProperty("time_stamp")
    private LocalDateTime timeStamp;
    
    @Column(name = "total_amount")
    @JsonProperty("total_amount")
    private BigDecimal totalAmount;
    
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    UsersModel user;
    
    @ManyToOne
    @JoinColumn(name = "shop_id", insertable = false, updatable = false)
    ShopsModel shop;
}