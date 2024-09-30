package com.example.gnb.expense.dto;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class ModifyExpenseRequest {

    private String expenseType;
    private String usageContent;
    private LocalDateTime usedAt;
    private Long usagePrice;
    private String expenseMemo;
}
