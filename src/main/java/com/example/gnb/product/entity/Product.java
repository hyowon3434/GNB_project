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
    @Column(nullable = false, name = "userEmail")
    @Setter
    private String userEmail;
    @Setter
    @Column(nullable = false, name = "productName")
    private String productName;
    @Setter
    @Column(nullable = false, name = "sellingPrice")
    private Integer sellingPrice;
    @Setter
    @Column(nullable = false, name = "purchasePrice")
    private Integer purchasePrice;
    @Setter
    @Column(name = "shippingCharge")
    private Integer shippingCharge;
    @Setter
    @Column(name = "isVat")
    private Boolean isVat;
    @Setter
    @Column(name = "salesExpenses")
    private Integer salesExpenses;
    @Setter
    @Column(name = "extraExpenses")
    private Integer extraExpenses;
    @Setter
    @Column(name = "platformFee")
    private Integer platformFee;
    @Setter
    @Column(name = "platformFeePer")
    private Float platformFeePer;
    @Setter
    @Column(name = "isFreeShipping")
    private Boolean isFreeShipping;
    @CreationTimestamp // insert 쿼리문이 발생했을때, 현재시간 값을 적용
    @Column(name = "createdAt")
    private Timestamp createdAt;
    @UpdateTimestamp // update 쿼리문이 발생했을때, 현재시간 값을 적용
    @Column(name = "updatedAt")
    private Timestamp updatedAt;
    @Setter
    @Column(name = "vatInvoice")
    private Integer vatInvoice;
    @Setter
    @Column(name = "taxServicePayment")
    private Integer taxServicePayment;
    @Setter
    @Column(name = "netProfit")
    private Integer netProfit;
    @Setter
    @Column(name = "marginRate")
    private Float marginRate;

    @Builder
    public Product(Long productId, String productName, Integer sellingPrice,
                   Integer purchasePrice, Integer shippingCharge, Boolean isVat,
                   Integer salesExpenses, Integer extraExpenses, Integer platformFee,
                   Float platformFeePer, Boolean isFreeShipping, Integer vatInvoice,
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
