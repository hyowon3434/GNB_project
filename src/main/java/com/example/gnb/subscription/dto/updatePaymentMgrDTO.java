package com.example.gnb.subscription.dto;

import lombok.Data;

@Data
public class updatePaymentMgrDTO {
    private Long SubscriptionId;
    private String mgrName;
    private String mgrPhone;
    private String mgrEmail;
}
