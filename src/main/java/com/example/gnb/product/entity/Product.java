package com.example.gnb.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long product_id;
    @Setter
    private String product_name;
    @Setter
    private int selling_price;
    @Setter
    private int purchase_price;
    @Setter
    private int shipping_charge;
    @Setter
    private boolean is_vat;
    @Setter
    private int sales_expenses;
    @Setter
    private int extra_expenses;
    @Setter
    private int platform_fee;
    @Setter
    private float platform_fee_per;
    @Setter
    private boolean is_free_shipping;
    @CreationTimestamp
    private Timestamp created_At;
    @UpdateTimestamp
    private Timestamp updated_At;
    @Setter
    @Column
    private int vat_invoice;
    @Setter
    private int tax_service_payment;
    @Setter
    private int net_profit;
    @Setter
    private float margin_Rate;

    @Builder
    public Product(String product_name, int selling_price, int purchase_price, int shipping_charge, boolean is_vat,
                   int sales_expenses, int extra_expenses, int platform_fee, float platform_fee_per, boolean is_free_shipping
                    , int vat_invoice, int tax_service_payment, int net_profit, float margin_Rate){
        this.product_name = product_name;
        this.selling_price = selling_price;
        this.purchase_price = purchase_price;
        this.shipping_charge = shipping_charge;
        this.is_vat = is_vat;
        this.sales_expenses = sales_expenses;
        this.extra_expenses = extra_expenses;
        this.platform_fee = platform_fee;
        this.platform_fee_per = platform_fee_per;
        this.is_free_shipping = is_free_shipping;
        this.vat_invoice = vat_invoice;
        this.tax_service_payment = tax_service_payment;
        this.net_profit = net_profit;
        this.margin_Rate = margin_Rate;
    }

}
