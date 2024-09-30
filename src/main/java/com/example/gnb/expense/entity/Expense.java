package com.example.gnb.expense.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "expense")
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "expenseId")
    private Long expenseId;
    @Setter
    @Column(nullable = false, name = "userEmail")
    private String userEmail;
    @Setter
    @Column(nullable = false, name = "expenseType")
    private String expenseType;
    @Setter
    @Column(nullable = true, name = "usageContent")
    private String usageContent;
    @Setter
    @Column(nullable = true, name = "usedAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime usedAt;
    @Setter
    @Column(nullable = false, name = "usagePrice")
    private Long usagePrice;
    @Setter
    @Column(nullable = true, name = "expenseMemo")
    private String expenseMemo;

    @Builder
    public Expense(Long expenseId, String userEmail, String expenseType, String usage_content,
                   LocalDateTime usedAt, Long usagePrice, String expenseMemo){
        this.expenseId = expenseId;
        this.userEmail = userEmail;
        this.expenseType = expenseType;
        this.usageContent = usage_content;
        this.usedAt = usedAt;
        this.usagePrice = usagePrice;
        this.expenseMemo = expenseMemo;
    }



}
