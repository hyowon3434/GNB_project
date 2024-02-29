package com.example.gnb.expense.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class RegisterExpenseRequest {

    private String expenseType;
    private String usageContent;
    private Long usagePrice;
    private String expenseMemo;
    private Timestamp usedAt;
}
