package com.example.gnb.expense.repository;

import com.example.gnb.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    Expense findByExpenseIdAndUserEmail(Long expenseId, String userEmail);
    List<Expense> deleteExpenseByExpenseIdAndUserEmail(Long expenseId, String userEmail);
    List<Expense> deleteExpenseByUserEmail(String userEmail);
    List<Expense> findByUserEmail(String userEmail);
}
