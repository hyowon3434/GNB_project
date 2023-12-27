package com.example.gnb.expense.service;

import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    // 경비지출내역 등록
    public Expense createExpense(RegisterExpenseRequest request){

        Expense expense = Expense.builder()
                        .expenseMemo(request.getExpenseMemo())
                        .expenseType(request.getExpenseType())
                        .usage_content(request.getUsage_content())
                        .usagePrice(request.getUsagePrice())
                        .build();


        Expense saved = expenseRepository.save(expense);

        return expenseRepository.findById(saved.getExpenseId()).get();
    }
}
