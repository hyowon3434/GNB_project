package com.example.gnb.expense.controller;

import com.example.gnb.config.auth.PrincipalDetails;
import com.example.gnb.expense.dto.ModifyExpenseRequest;
import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.service.ExpenseService;
import com.example.gnb.user.entity.User;
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
    public ResponseEntity<?> registerExpense(@RequestBody RegisterExpenseRequest registerExpenseRequest, HttpSession session){
        Expense registeredExpense = expenseService.createExpense(registerExpenseRequest, getCurrentUserEmail(session));
        return ResponseEntity.ok(registeredExpense);
    }

    // 전체 경비지출내역 조회 기능
    @GetMapping
    public ResponseEntity<?> getAllExpenses(HttpSession session){

        return ResponseEntity.ok(expenseService.findAllExpenses(getCurrentUserEmail(session)));
    }

    // 선택 경비지출내역 조회
    @GetMapping("/get")
    public ResponseEntity<?> getSelectedExepnse(@RequestParam("expenseId") Long expenseId, HttpSession session){
        return ResponseEntity.ok(expenseService.findSelectedExpense(expenseId, getCurrentUserEmail(session)));
    }

    // 선택 경비지출내역 수정
    @PutMapping
    public ResponseEntity<?> modifySelectedExpense(@RequestParam("expenseId") Long expenseId,
                                                   @RequestBody ModifyExpenseRequest request,
                                                   HttpSession session){
        return ResponseEntity.ok(expenseService.modifyExpense(expenseId, request, getCurrentUserEmail(session)));
    }

    // 선택 경비지출내역 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteSelectedExpense(@RequestParam("expenseId") Long expenseId, HttpSession session){
        return ResponseEntity.ok(expenseService.deleteExpense(expenseId, getCurrentUserEmail(session)));
    }

    // 전체 경비지출내역 삭제
    @DeleteMapping
    public ResponseEntity<?> deletAllExpenses(HttpSession session){
        return ResponseEntity.ok(expenseService.deleteExpenses(getCurrentUserEmail(session)));
    }

    private String getCurrentUserEmail(HttpSession session){
        User currentUser = (User)session.getAttribute("user");
        if (currentUser == null) {
            throw new NullPointerException("현재 유저 정보가 없습니다");
        }
        return currentUser.getEmail();
    }

}
