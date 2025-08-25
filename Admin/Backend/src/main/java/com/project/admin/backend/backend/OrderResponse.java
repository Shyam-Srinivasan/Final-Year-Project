package com.project.admin.backend.backend;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Long userId,
        String userName,
        String shopName,
        BigDecimal totalAmount,
        Boolean isPurchased,
        LocalDateTime timeStamp,
        List<String> itemNames,
        List<Integer> quantities
) { }
