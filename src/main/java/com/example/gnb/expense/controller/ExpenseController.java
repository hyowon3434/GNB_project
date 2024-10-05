package com.example.gnb.expense.controller;

import com.example.gnb.config.auth.PrincipalDetails;
import com.example.gnb.config.jwt.JwtTokenProvider;
import com.example.gnb.expense.dto.ModifyExpenseRequest;
import com.example.gnb.expense.dto.RegisterExpenseRequest;
import com.example.gnb.expense.entity.Expense;
import com.example.gnb.expense.service.ExpenseService;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "*")
@RequestMapping("/expense")
@Slf4j
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider tokenProvider;

    // 경비지출 등록 기능
    @PostMapping
    public ResponseEntity<?> registerExpense(@RequestBody RegisterExpenseRequest registerExpenseRequest,
                                             @RequestHeader("Authorization") String authorization){
        Expense registeredExpense = expenseService.createExpense(registerExpenseRequest, getCurrentUserEmail(authorization));
        log.warn(registeredExpense.getUsedAt().toString());
        return ResponseEntity.ok(registeredExpense);
    }

    // 전체 경비지출내역 조회 기능
    @GetMapping
    public ResponseEntity<?> getAllExpenses(@RequestHeader("Authorization") String authorization){

        return ResponseEntity.ok(expenseService.findAllExpenses(getCurrentUserEmail(authorization)));
    }

    // 선택 경비지출내역 조회
    @GetMapping("/get")
    public ResponseEntity<?> getSelectedExepnse(@RequestParam("expenseId") Long expenseId,
                                                @RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok(expenseService.findSelectedExpense(expenseId, getCurrentUserEmail(authorization)));
    }

    // 선택 경비지출내역 수정
    @PutMapping
    public ResponseEntity<?> modifySelectedExpense(@RequestParam("expenseId") Long expenseId,
                                                   @RequestBody ModifyExpenseRequest request,
                                                   @RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok(expenseService.modifyExpense(expenseId, request, getCurrentUserEmail(authorization)));
    }

    // 선택 경비지출내역 삭제
    @DeleteMapping("/one")
    public ResponseEntity<?> deleteSelectedExpense(@RequestParam("expenseId") Long expenseId,
                                                   @RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok(expenseService.deleteExpense(expenseId, getCurrentUserEmail(authorization)));
    }

    // 전체 경비지출내역 삭제
    @DeleteMapping
    public ResponseEntity<?> deletAllExpenses(@RequestHeader("Authorization") String authorization){
        return ResponseEntity.ok(expenseService.deleteExpenses(getCurrentUserEmail(authorization)));
    }

    private String getCurrentUserEmail(String authorization){
        String token = authorization.substring(6);
        log.warn(token);
        String userEmail = tokenProvider.getUserEmailFromJWT(token);

        User currentUser = userRepository.findByEmail(userEmail);
        if (currentUser == null) {
            throw new NullPointerException("현재 유저 정보가 없습니다");
        }
        return currentUser.getEmail();
    }

}
