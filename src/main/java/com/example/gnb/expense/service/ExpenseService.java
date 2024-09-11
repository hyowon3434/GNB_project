package com.example.gnb.expense.service;

import com.example.gnb.expense.dto.ModifyExpenseRequest;
import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.repository.ExpenseRepository;
import com.example.gnb.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    // 경비지출내역 등록
    public Expense createExpense(RegisterExpenseRequest request, String userEmail){
        if (userEmail == null && userEmail.equals("anonymousUser")) throw new NullPointerException("세션에 유저정보가 없습니다.");

        Expense expense = Expense.builder()
                        .userEmail(userEmail)
                        .expenseMemo(request.getExpenseMemo())
                        .expenseType(request.getExpenseType())
                        .usage_content(request.getUsageContent())
                        .usagePrice(request.getUsagePrice())
                        .build();


        Expense saved = expenseRepository.save(expense);

        return expenseRepository.findById(saved.getExpenseId()).get();
    }

    // 전체 경비지출내역 조회
    public List<Expense> findAllExpenses(String userEmail){

        return expenseRepository.findByUserEmail(userEmail);
    }

    // 선택 경비지출내역 조회
    public Expense findSelectedExpense(Long expenseId, String userEmail){
        return expenseRepository.findByExpenseIdAndUserEmail(expenseId, userEmail);
    }

    // 선택 경비지출내역 수정
    public List<Expense> modifyExpense(Long expense_id,
                                       ModifyExpenseRequest request,
                                       String userEmail){

       Expense expense = expenseRepository.findByExpenseIdAndUserEmail(expense_id, userEmail);
       expense.setUserEmail(userEmail);
       expense.setExpenseType(request.getExpenseType());
       expense.setExpenseMemo(request.getExpenseMemo());
       expense.setUsedAt(request.getUsedAt());
       expense.setUsagePrice(request.getUsagePrice());
       expense.setUsageContent(request.getUsageContent());

       expenseRepository.save(expense);
       return expenseRepository.findByUserEmail(userEmail);
    }

    // 선택 경비지출내역 삭제
    public List<Expense> deleteExpense(Long expenseId, String userEmail){

        expenseRepository.deleteExpenseByExpenseIdAndUserEmail(expenseId, userEmail);
        return expenseRepository.findByUserEmail(userEmail);
    }

    // 전체 경비지출내역 삭제
    public List<Expense> deleteExpenses(String userEmail){
        expenseRepository.deleteExpenseByUserEmail(userEmail);
        return expenseRepository.findByUserEmail(userEmail);
    }

//    private String getLoggedUserEmail(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        SecurityContextHolder.getContext();
//        if (authentication == null) {
//            return null;
//        }
//        log.warn("현재 로그인한 사용자 email : " + authentication.getPrincipal());
//        return authentication.getName();
//    }
}
