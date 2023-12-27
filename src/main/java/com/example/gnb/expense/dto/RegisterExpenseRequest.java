package com.example.gnb.expense.dto;

import lombok.Getter;

@Getter
public class RegisterExpenseRequest {

    private String expenseType;
    private String usage_content;
    private Long usagePrice;
    private String expenseMemo;
}
