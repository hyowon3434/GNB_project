package com.example.gnb.product.dto;

import lombok.Getter;

@Getter
public class UpdateProductRequest {

    private String productName;
    private Integer sellingPrice;
    private Integer purchasePrice;
    private Integer shippingCharge;
    private Boolean isVat;
    private Integer salesExpenses;
    private Integer extraExpenses;
    private Integer platformFee;
    private Float platformFeePer;
    private Boolean isFreeShipping;

}
