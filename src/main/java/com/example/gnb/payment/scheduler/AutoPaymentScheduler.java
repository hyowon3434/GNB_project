package com.example.gnb.payment.scheduler;

import com.example.gnb.payment.service.TossPaymentService;
import com.example.gnb.subscription.entity.Subscription;
import com.example.gnb.subscription.repository.SubscriptionRepository;
import com.example.gnb.user.entity.User;
import com.example.gnb.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class AutoPaymentScheduler {

    private static final Logger logger = LoggerFactory.getLogger(AutoPaymentScheduler.class);

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private TossPaymentService tossPaymentService;
    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 1 * * ?")
    public void processAutoPayments(){
        logger.info("Starting auto payment process");

        LocalDateTime now = LocalDateTime.now();
        List<Subscription> subscriptionsToRenew = subscriptionRepository.findByAutoRenewalTrueAndEndDateBefore(now);

        for (Subscription subscription : subscriptionsToRenew) {
            try {
                User user = subscription.getUser();
                String billingKey = user.getBillingKey();

                if (billingKey == null) {
                    logger.warn("Billing key not found for user: {}", user.getUserId());
                    continue;
                }

                // 결제처리
                tossPaymentService.processSubscriptionPayment(subscription, billingKey);

                // 구독갱신
                subscription.setStartDate(now);
                subscription.setEndDate(now.plusMonths(1));
                subscriptionRepository.save(subscription);

                //사용자 정보 업데이트
                user.setPlanBeginAt(subscription.getStartDate());
                user.setPlanFinishAt(subscription.getEndDate());
                userRepository.save(user);

                logger.info("Successfully renewed subscription for user: {}", user.getUserId());

            }catch (Exception e){
                logger.error("Failed to process payment for subscription: {}", subscription.getSubscriptionId(), e);
            }
        }

        logger.info("Completed auto payment process");
    }

}
