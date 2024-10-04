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
    @JoinColumn(name = "userId")
    private User user;
    @Column(nullable = false, name = "planType")
    private String planType;
    @Column(nullable = false, name = "price")
    private BigDecimal price;
    @Column(name = "mgrName")
    private String mgrName;
    @Column(name = "mgrPhone")
    private String mgrPhone;
    @Column(name = "mgrEmail")
    private String mgrEmail;
    @Column(nullable = false, name = "startDate")
    private LocalDateTime startDate;
    @Column(nullable = false, name = "endDate")
    private LocalDateTime endDate;
    @Column(nullable = false, name = "autoRenewal")
    private boolean autoRenewal;
    @CreationTimestamp
    @Column(nullable = false, name = "createdAt")
    private LocalDateTime createdAt;

    @Builder
    public Subscription(User user, String planType,
                        BigDecimal price, LocalDateTime startDate,
                        LocalDateTime endDate, boolean autoRenewal,
                        String mgrName, String mgrPhone, String mgrEmail) {
        this.user = user;
        this.planType = planType;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.autoRenewal = autoRenewal;
        this.mgrName = mgrName;
        this.mgrPhone = mgrPhone;
        this.mgrEmail = mgrEmail;
    }
}
