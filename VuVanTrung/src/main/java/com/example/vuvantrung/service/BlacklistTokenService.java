package com.example.vuvantrung.service;

import com.example.vuvantrung.repository.BlacklistedTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BlacklistTokenService {

    @Autowired
    private BlacklistedTokenRepository blacklistedTokenRepository;

    @Async
    public void removeTokensAsync() {
        blacklistedTokenRepository.deleteExpiredTokens();
        log.info("Clear token in database after a long day");
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void removeTokenScheduled(){
        removeTokensAsync();
    }
}
