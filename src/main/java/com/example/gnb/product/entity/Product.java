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
    private String productName;
    @Setter
    private int sellingPrice;
    @Setter
    private int purchasePrice;
    @Setter
    private int shippingCharge;
    @Setter
    private String isVat;
    @Setter
    private int salesExpenses;
    @Setter
    private int extraExpenses;
    @Setter
    private int platformFee;
    @Setter
    private float platformFeePer;
    @Setter
    private String isFreeShipping;
    @CreationTimestamp // insert 쿼리문이 발생했을때, 현재시간 값을 적용
    private Timestamp createdAt;
    @UpdateTimestamp // update 쿼리문이 발생했을때, 현재시간 값을 적용
    private Timestamp updatedAt;
    @Setter
    @Column
    private int vatInvoice;
    @Setter
    private int taxServicePayment;
    @Setter
    private int netProfit;
    @Setter
    private float marginRate;

    @Builder
    public Product(String productName, int sellingPrice, int purchasePrice, int shippingCharge, String isVat,
                   int salesExpenses, int extraExpenses, int platformFee, float platformFeePer, String isFreeShipping
                    , int vatInvoice, int taxServicePayment, int netProfit, float marginRate){
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.shippingCharge = shippingCharge;
        this.isVat = isVat;
        this.salesExpenses = salesExpenses;
        this.extraExpenses = extraExpenses;
        this.platformFee = platformFee;
        this.platformFeePer = platformFeePer;
        this.isFreeShipping = isFreeShipping;
        this.vatInvoice = vatInvoice;
        this.taxServicePayment = taxServicePayment;
        this.netProfit = netProfit;
        this.marginRate = marginRate;
    }

}
