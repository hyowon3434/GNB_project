package com.example.gnb.subscription.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class SubscriptionDTO {
    private Long userId;
    private String planType;
    private BigDecimal price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean autoRenewal;
    private String mgrName;
    private String mgrPhone;
    private String mgrEmail;
}
