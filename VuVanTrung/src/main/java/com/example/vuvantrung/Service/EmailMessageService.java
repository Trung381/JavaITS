package com.example.vuvantrung.Service;

import org.springframework.stereotype.Component;

@Component  // Đánh dấu class này là một bean trong IoC container
public class EmailMessageService implements MessageService {

    @Override
    public void sendMessage(String message, String recipient) {
        // Mô phỏng việc gửi email bằng cách in ra console
        System.out.println("Email sent to: " + recipient);
        System.out.println("Message: " + message);
    }
}
