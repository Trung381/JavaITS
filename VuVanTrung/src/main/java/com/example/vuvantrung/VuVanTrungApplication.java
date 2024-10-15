package com.example.vuvantrung;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
@RestController
@EnableAsync
@EnableScheduling
public class VuVanTrungApplication {
    private static final Logger logger = LoggerFactory.getLogger(VuVanTrungApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(VuVanTrungApplication.class, args);

        logger.info("Thông điệp INFO");
        logger.warn("Thông điệp cảnh báo (WARN)");
        logger.error("Thông điệp lỗi (ERROR)");
    }
}