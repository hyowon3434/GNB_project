package com.example.gnb.subscription.entity;

import com.example.gnb.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "subscription")
@Data
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private String planType;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @Column(nullable = false)
    private boolean autoRenewal;
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Subscription(User user, String planType,
                        BigDecimal price, LocalDateTime startDate,
                        LocalDateTime endDate, boolean autoRenewal) {
        this.user = user;
        this.planType = planType;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.autoRenewal = autoRenewal;
    }
}
