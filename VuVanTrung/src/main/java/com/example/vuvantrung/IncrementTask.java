package com.example.vuvantrung;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class IncrementTask {

    private int counter = 0;

    // Phương thức này sẽ được gọi tự động mỗi 5 giây
//    @Scheduled(fixedRate = 5000)
    public void incrementCounter() {
        counter++;
        System.out.println("Current Counter: " + counter);
    }

    // Phương thức để lấy giá trị của counter
    public int getCounter() {
        return counter;
    }
}
