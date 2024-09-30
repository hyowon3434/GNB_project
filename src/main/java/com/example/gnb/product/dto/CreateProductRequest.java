package com.example.gnb.product.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class CreateProductRequest {

    private String productName;
    private Integer sellingPrice;
    private Integer purchasePrice;
    private Integer shippingCharge;
    private String isVat;
    private Integer salesExpenses;
    private Integer extraExpenses;
    private Integer platformFee;
    private Float platformFeePer;
    private String isFreeShipping;

}
