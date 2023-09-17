package com.example.gnb.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "margin_rate")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MarginRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long margin_id;
    @Column
    private Long product_id;
    @Setter
    @Column
    private int vat_invoice;
    @Setter
    @Column
    private int tax_service_payment;
    @Setter
    @Column
    private int net_profit;
    @Setter
    @Column
    private float margin_rate;

    @Builder
    public MarginRate(Long product_id, int vat_invoice, int tax_service_payment, int net_profit, float margin_rate){
        this.product_id = product_id;
        this.vat_invoice = vat_invoice;
        this.tax_service_payment = tax_service_payment;
        this.net_profit = net_profit;
        this.margin_rate = margin_rate;
    }
}
