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
    @Setter
    private String expenseType;
    @Setter
    private String usage_content;
    @Setter
    private Timestamp usedAt;
    @Setter
    private Long usagePrice;
    @Setter
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
