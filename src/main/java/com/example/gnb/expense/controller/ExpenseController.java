package com.example.gnb.expense.controller;

import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    // 전체 경비지출내역 조회 기능
    @GetMapping
    public List<Expense> getAllExpenses(){
        return expenseService.findAllExpenses();
    }

    // 선택 경비지출내역 조회
    @GetMapping("/{expense_id}")
    public Expense getSelectedExepnse(@PathVariable("expense_id") Long expense_id){

        return expenseService.findSelectedExpense(expense_id);
    }

}