package com.example.gnb.expense.repository;

import com.example.gnb.expense.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    Expense findByExpenseIdAndUserId(Long expenseId, Long userId);
    List<Expense> deleteExpenseByExpenseIdAndUserId(Long expenseId, Long userId);
    List<Expense> deleteExpenseByUserId(Long userId);
}
