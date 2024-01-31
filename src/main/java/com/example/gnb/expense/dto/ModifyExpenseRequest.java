package com.example.gnb.expense.dto;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ModifyExpenseRequest {

    private String expenseType;
    private String usageContent;
    private Timestamp usedAt;
    private Long usagePrice;
    private String expenseMemo;
}
