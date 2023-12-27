package com.example.gnb.product.dto;

import lombok.Getter;

@Getter
public class UpdateProductRequest {

    private String product_name;
    private int selling_price;
    private int purchase_price;
    private int shipping_charge;
    private String is_vat;
    private int sales_expenses;
    private int extra_expenses;
    private int platform_fee;
    private float platform_fee_per;
    private String is_free_shipping;

}
