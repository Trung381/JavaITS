package com.example.vuvantrung.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class EmailJobListener extends JobListenerSupport {

    @Override
    public String getName() {
        return "EmailJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("Job " + context.getJobDetail().getKey() + " is about to be executed.");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("Job " + context.getJobDetail().getKey() + " was executed.");
        if (jobException != null) {
            System.err.println("Job encountered an exception: " + jobException.getMessage());
        }
    }
}
