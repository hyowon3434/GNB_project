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
    private Long productId;
    @Column(nullable = false)
    @Setter
    private String userEmail;
    @Setter
    private String productName;
    @Setter
    private Integer sellingPrice;
    @Setter
    private Integer purchasePrice;
    @Setter
    private Integer shippingCharge;
    @Setter
    private String isVat;
    @Setter
    private Integer salesExpenses;
    @Setter
    private Integer extraExpenses;
    @Setter
    private Integer platformFee;
    @Setter
    private Float platformFeePer;
    @Setter
    private String isFreeShipping;
    @CreationTimestamp // insert 쿼리문이 발생했을때, 현재시간 값을 적용
    private Timestamp createdAt;
    @UpdateTimestamp // update 쿼리문이 발생했을때, 현재시간 값을 적용
    private Timestamp updatedAt;
    @Setter
    @Column
    private Integer vatInvoice;
    @Setter
    private Integer taxServicePayment;
    @Setter
    private Integer netProfit;
    @Setter
    private Float marginRate;

    @Builder
    public Product(Long productId, String productName, Integer sellingPrice,
                   Integer purchasePrice, Integer shippingCharge, String isVat,
                   Integer salesExpenses, Integer extraExpenses, Integer platformFee,
                   Float platformFeePer, String isFreeShipping, Integer vatInvoice,
                   Integer taxServicePayment, Integer netProfit, Float marginRate,
                   String userEmail){
        this.productId = productId;
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
        this.userEmail = userEmail;
    }

}
