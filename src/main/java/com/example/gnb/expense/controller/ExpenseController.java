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
    @PostMapping("/{userId}")
    public Expense registerExpense(@RequestBody RegisterExpenseRequest registerExpenseRequest,
                                   @PathVariable("userId") Long userId){
        Expense registeredExpense = expenseService.createExpense(registerExpenseRequest, userId);
        return registeredExpense;
    }

    // 전체 경비지출내역 조회 기능
    @GetMapping("/{userId}")
    public List<Expense> getAllExpenses(@PathVariable("userId") Long userId){
        return expenseService.findAllExpenses(userId);
    }

    // 선택 경비지출내역 조회
    @GetMapping("/{expenseId}/{userId}")
    public Expense getSelectedExepnse(@PathVariable("expenseId") Long expenseId,
                                      @PathVariable("userId") Long userId){
        return expenseService.findSelectedExpense(expenseId, userId);
    }

    // 선택 경비지출내역 수정
    @PutMapping("/{expenseId}/{userId}")
    public List<Expense> modifySelectedExpense(@PathVariable("expenseId") Long expenseId,
                                               @PathVariable("userId") Long userId,
                                               @RequestBody ModifyExpenseRequest request){
        return expenseService.modifyExpense(expenseId,userId, request);
    }

    // 선택 경비지출내역 삭제
    @DeleteMapping("/{expenseId}/{userId}")
    public List<Expense> deleteSelectedExpense(@PathVariable("expenseId") Long expenseId,
                                               @PathVariable("userId") Long userId){
        return expenseService.deleteExpense(expenseId, userId);
    }

    // 전체 경비지출내역 삭제
    @DeleteMapping("/{userId}")
    public List<Expense> deletAllExpenses(@PathVariable("userId") Long userId){
        return expenseService.deleteExpenses(userId);
    }

}
