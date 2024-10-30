package com.example.vuvantrung.service;

import com.example.vuvantrung.entity.UsageHistory;
import com.example.vuvantrung.entity.User;
import com.example.vuvantrung.repository.usageHistory.UsageHistoryRepository;
import com.example.vuvantrung.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private UsageHistoryRepository usageHistoryRepository;

    @Autowired
    private UserRepository userRepository;

//    @Scheduled(fixedRate = 2000) // 600000ms = 10 phút
    @Scheduled(cron = "0 0 0 */30 * *")
    public void sendEmails() throws Exception {
        List<UsageHistory> usageHistories = usageHistoryRepository.findByUserIsNotNull();

        for (UsageHistory usage : usageHistories) {
            User user = usage.getUser();
            double amount = usage.getAmount();

            sendEmailAsync(user.getEmail(), amount);
        }
    }


    @Async
    public void sendEmailAsync(String email, double amount) throws Exception{
        // giaar laapj gửi email vì qua smtp nó mất mấy giây mới đc : )))
        Thread.sleep(3000);
        String notice = "Gửi email tới: " + email + ", Số tiền điện: " + amount;
        System.out.println(notice);
        log.info(notice);
    }
}
