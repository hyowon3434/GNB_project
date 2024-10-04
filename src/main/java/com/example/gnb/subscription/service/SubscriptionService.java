package com.example.gnb.subscription.service;

import com.example.gnb.subscription.dto.SubscriptionDTO;
import com.example.gnb.subscription.dto.updatePaymentMgrDTO;
import com.example.gnb.subscription.entity.Subscription;
import com.example.gnb.subscription.repository.SubscriptionRepository;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public SubscriptionDTO subscribe(SubscriptionDTO dto){
        User sessionUser = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dto.setUserId(sessionUser.getUserId());
        User user = userRepository.findById(dto.getUserId()).get();

        BigDecimal price = calculatePrice(dto.getPlanType());

        Subscription subscription = Subscription.builder()
                .user(user)
                .planType(dto.getPlanType())
                .price(price)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .autoRenewal(dto.isAutoRenewal())
                .build();

        subscriptionRepository.save(subscription);

        user.setUserPlan(subscription.getPlanType());
        user.setPlanBeginAt(subscription.getStartDate());
        user.setPlanFinishAt(subscription.getEndDate());
        user.setAutoRenewal(subscription.isAutoRenewal());

        return convertToDTO(subscription);
    }

    public Subscription updatePaymentMgr(updatePaymentMgrDTO request){
        Subscription subscription = subscriptionRepository.findBySubscriptionId(request.getSubscriptionId());
        subscription.setMgrName(request.getMgrEmail());
        subscription.setMgrPhone(request.getMgrPhone());
        subscription.setMgrEmail(request.getMgrEmail());

        subscriptionRepository.save(subscription);

        return subscription;
    }

    private BigDecimal calculatePrice(String planType){
        BigDecimal basePrice;
        switch (planType){
            case "STARTER":
                return BigDecimal.ZERO;
            case "STANDARD":
                basePrice = new BigDecimal("69000");
                break;
            case "PRO_SELLER":
                basePrice = new BigDecimal("129000");
                break;
            default:
                throw new IllegalArgumentException("Invalid plan Type");
        }
        // 부가가치세 포함가격 설정
        return basePrice.add(basePrice.multiply(new BigDecimal("0.1")));
    }

    private SubscriptionDTO convertToDTO(Subscription subscription){
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setUserId(subscription.getUser().getUserId());
        dto.setPlanType(subscription.getPlanType());
        dto.setPrice(subscription.getPrice());
        dto.setStartDate(subscription.getStartDate());
        dto.setEndDate(subscription.getEndDate());
        dto.setAutoRenewal(subscription.isAutoRenewal());

        return dto;
    }
}
