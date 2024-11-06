package com.example.vuvantrung.scheduler;

import com.example.vuvantrung.service.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailJob implements Job {

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            emailService.sendEmails();
        } catch (Exception e) {
            throw new JobExecutionException("Error sending emails", e);
        }
    }
}
