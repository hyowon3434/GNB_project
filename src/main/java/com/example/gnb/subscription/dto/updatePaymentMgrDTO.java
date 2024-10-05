package com.example.gnb.subscription.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class updatePaymentMgrDTO {
    private Long subscriptionId;
    private String mgrName;
    private String mgrPhone;
    private String mgrEmail;
}
