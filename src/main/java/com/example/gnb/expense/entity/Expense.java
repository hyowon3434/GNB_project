package com.example.gnb.expense.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "expense")
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long expenseId;
    @Column(nullable = false)
    private Long userId;
    @Setter
    @Column(nullable = false)
    private String expenseType;
    @Setter
    @Column(nullable = false)
    private String usageContent;
    @Setter
    private Timestamp usedAt;
    @Setter
    @Column(nullable = false)
    private Long usagePrice;
    @Setter
    @Column(nullable = false)
    private String expenseMemo;

    @Builder
    public Expense(Long expenseId, Long userId, String expenseType, String usage_content,
                   Timestamp usedAt, Long usagePrice, String expenseMemo){
        this.expenseId = expenseId;
        this.userId = userId;
        this.expenseType = expenseType;
        this.usageContent = usage_content;
        this.usedAt = usedAt;
        this.usagePrice = usagePrice;
        this.expenseMemo = expenseMemo;
    }



}
