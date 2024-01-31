package com.example.gnb.expense.service;

import com.example.gnb.expense.dto.ModifyExpenseRequest;
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
                        .usage_content(request.getUsageContent())
                        .usagePrice(request.getUsagePrice())
                        .build();


        Expense saved = expenseRepository.save(expense);

        return expenseRepository.findById(saved.getExpenseId()).get();
    }

    // 전체 경비지출내역 조회
    public List<Expense> findAllExpenses(){
        return expenseRepository.findAll();
    }

    // 선택 경비지출내역 조회
    public Expense findSelectedExpense(Long id){
        return expenseRepository.findById(id).get();
    }

    // 선택 경비지출내역 수정
    public List<Expense> modifyExpense(Long expense_id, ModifyExpenseRequest request){
       Expense expense = expenseRepository.findById(expense_id).get();
       expense.setExpenseType(request.getExpenseType());
       expense.setExpenseMemo(request.getExpenseMemo());
       expense.setUsedAt(request.getUsedAt());
       expense.setUsagePrice(request.getUsagePrice());
       expense.setUsageContent(request.getUsageContent());

       expenseRepository.save(expense);

       return expenseRepository.findAll();
    }

    // 선택 경비지출내역 삭제
    public List<Expense> deleteExpense(Long expense_id){
        expenseRepository.deleteById(expense_id);
        return expenseRepository.findAll();
    }

    // 전체 경비지출내역 삭제
    public List<Expense> deleteExpenses(){
        expenseRepository.deleteAll();
        return expenseRepository.findAll();
    }
}
