package com.example.gnb.expense.controller;

import com.example.gnb.config.auth.PrincipalDetails;
import com.example.gnb.expense.dto.ModifyExpenseRequest;
import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.service.ExpenseService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@RequestMapping("/expense")
@Slf4j
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // 경비지출 등록 기능
    @PostMapping
    public ResponseEntity<?> registerExpense(@RequestBody RegisterExpenseRequest registerExpenseRequest,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails){
        log.warn(registerExpenseRequest.toString());
        Expense registeredExpense = expenseService.createExpense(registerExpenseRequest, principalDetails.getUsername());
        return ResponseEntity.ok(registeredExpense);
    }

    // 전체 경비지출내역 조회 기능
    @GetMapping
    public ResponseEntity<?> getAllExpenses(){
        return ResponseEntity.ok(expenseService.findAllExpenses());
    }

    // 선택 경비지출내역 조회
    @GetMapping("/select")
    public ResponseEntity<?> getSelectedExepnse(@RequestParam("expenseId") Long expenseId){
        return ResponseEntity.ok(expenseService.findSelectedExpense(expenseId));
    }

    // 선택 경비지출내역 수정
    @PutMapping("/select")
    public ResponseEntity<?> modifySelectedExpense(@RequestParam("expenseId") Long expenseId,
                                                   @RequestBody ModifyExpenseRequest request){
        return ResponseEntity.ok(expenseService.modifyExpense(expenseId, request));
    }

    // 선택 경비지출내역 삭제
    @DeleteMapping("/select")
    public ResponseEntity<?> deleteSelectedExpense(@RequestParam("expenseId") Long expenseId){
        return ResponseEntity.ok(expenseService.deleteExpense(expenseId));
    }

    // 전체 경비지출내역 삭제
    @DeleteMapping
    public ResponseEntity<?> deletAllExpenses(){
        return ResponseEntity.ok(expenseService.deleteExpenses());
    }

}
