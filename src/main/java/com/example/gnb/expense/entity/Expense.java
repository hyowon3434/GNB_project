package com.example.gnb.expense.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "expense")
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "expenseId")
    private Long expenseId;
    @Setter
    @Column(nullable = false, name = "userEmail")
    private String userEmail;
    @Setter
    @Column(nullable = false, name = "expenseType")
    private String expenseType;
    @Setter
    @Column(nullable = true, name = "usageContent")
    private String usageContent;
    @Setter
    @Column(nullable = true, name = "usedAt")
    private String usedAt;
    @Setter
    @Column(nullable = false, name = "usagePrice")
    private Long usagePrice;
    @Setter
    @Column(nullable = true, name = "expenseMemo")
    private String expenseMemo;

    @Builder
    public Expense(Long expenseId, String userEmail, String expenseType, String usage_content,
                   String usedAt, Long usagePrice, String expenseMemo){
        this.expenseId = expenseId;
        this.userEmail = userEmail;
        this.expenseType = expenseType;
        this.usageContent = usage_content;
        this.usedAt = usedAt;
        this.usagePrice = usagePrice;
        this.expenseMemo = expenseMemo;
    }



}
