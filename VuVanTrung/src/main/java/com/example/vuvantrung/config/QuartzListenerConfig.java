package com.example.vuvantrung.config;

import com.example.vuvantrung.scheduler.EmailJobListener;
import com.example.vuvantrung.scheduler.EmailTriggerListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;


@Configuration
public class QuartzListenerConfig {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private EmailJobListener emailJobListener;

    @Autowired
    private EmailTriggerListener emailTriggerListener;

    @EventListener(ContextRefreshedEvent.class)
    public void registerListeners() throws SchedulerException {
        scheduler.getListenerManager().addJobListener(emailJobListener);
        scheduler.getListenerManager().addTriggerListener(emailTriggerListener);
    }
}

