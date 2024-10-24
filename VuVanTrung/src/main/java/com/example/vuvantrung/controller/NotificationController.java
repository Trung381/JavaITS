package com.example.vuvantrung.controller;

import com.example.vuvantrung.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Đánh dấu class này là một REST controller
public class NotificationController {

    private final MessageService messageService;

    @Autowired
    public NotificationController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/send")
    public String sendNotification(@RequestParam String message, @RequestParam String recipient) {
        // Gọi phương thức sendMessage từ messageService
        messageService.sendMessage(message, recipient);
        return "Notification sent!";
    }
}
