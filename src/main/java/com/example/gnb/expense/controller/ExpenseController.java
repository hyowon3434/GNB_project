package com.example.gnb.expense.controller;

import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    // 경비지출 등록 기능
    @PostMapping
    public Expense registerExpense(@RequestBody RegisterExpenseRequest registerExpenseRequest){
        Expense registeredExpense = expenseService.createExpense(registerExpenseRequest);
        return registeredExpense;
    }

}
