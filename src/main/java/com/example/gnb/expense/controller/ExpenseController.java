package com.example.gnb.expense.controller;

import com.example.gnb.expense.dto.ModifyExpenseRequest;
import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
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
    @GetMapping("/select")
    public Expense getSelectedExepnse(@RequestParam("expenseId") Long expenseId){
        return expenseService.findSelectedExpense(expenseId);
    }

    // 선택 경비지출내역 수정
    @PutMapping("/select")
    public List<Expense> modifySelectedExpense(@RequestParam("expenseId") Long expenseId,
                                               @RequestBody ModifyExpenseRequest request){
        return expenseService.modifyExpense(expenseId, request);
    }

    // 선택 경비지출내역 삭제
    @DeleteMapping("/select")
    public List<Expense> deleteSelectedExpense(@RequestParam("expenseId") Long expenseId){
        return expenseService.deleteExpense(expenseId);
    }

    // 전체 경비지출내역 삭제
    @DeleteMapping
    public List<Expense> deletAllExpenses(){
        return expenseService.deleteExpenses();
    }

}
