package com.example.gnb.product.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "product")
@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long product_id;
    @Setter
    private String product_name;
    @Setter
    private int selling_price;
    @Setter
    private int shipping_charge;
    @Setter
    private String is_vat;
    @Setter
    private int sales_expenses;
    @Setter
    private int extra_expenses;
    @Setter
    private int platform_fee;
    @Setter
    private float platform_fee_per;
    @Setter
    private String is_free_shipping;
    @Setter
    @CreatedDate
    private LocalDateTime created_At;

    @Builder
    public Product(String product_name, int selling_price, int shipping_charge, String is_vat, int sales_expenses,
                    int extra_expenses, int platform_fee, float platform_fee_per, String is_free_shipping){
        this.product_name = product_name;
        this.selling_price = selling_price;
        this.shipping_charge = shipping_charge;
        this.is_vat = is_vat;
        this.sales_expenses = sales_expenses;
        this.extra_expenses = extra_expenses;
        this.platform_fee = platform_fee;
        this.platform_fee_per = platform_fee_per;
        this.is_free_shipping = is_free_shipping;
    }

}
