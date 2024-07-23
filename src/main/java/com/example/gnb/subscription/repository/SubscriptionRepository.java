package com.example.gnb.subscription.repository;

import com.example.gnb.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByAutoRenewalTrueAndEndDateBefore(LocalDateTime now);
}
