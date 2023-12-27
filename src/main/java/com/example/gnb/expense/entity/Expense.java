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
    private Long expenseId;
    private String expenseType;
    private String usage_content;
    private Timestamp usedAt;
    private Long usagePrice;
    private String expenseMemo;

    @Builder
    public Expense(Long expenseId, String expenseType, String usage_content,
                   Timestamp usedAt, Long usagePrice, String expenseMemo){
        this.expenseId = expenseId;
        this.expenseType = expenseType;
        this.usage_content = usage_content;
        this.usedAt = usedAt;
        this.usagePrice = usagePrice;
        this.expenseMemo = expenseMemo;
    }



}
