package com.example.vuvantrung.config;

import com.example.vuvantrung.service.EmailService;
import com.example.vuvantrung.service.MyService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class SampleJob extends QuartzJobBean {

    @Autowired
    private MyService myService;  // Inject một service của Spring

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // Logic thực thi của job
        myService.performTask();
        System.out.println("Job executed!");
    }

    
}
