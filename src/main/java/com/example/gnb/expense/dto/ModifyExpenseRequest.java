package com.example.gnb.expense.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ModifyExpenseRequest {

    private String expenseType;
    private String usageContent;
    private String usedAt;
    private Long usagePrice;
    private String expenseMemo;
}
