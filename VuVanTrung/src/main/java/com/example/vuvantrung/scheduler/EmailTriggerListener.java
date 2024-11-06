package com.example.vuvantrung.scheduler;

import org.quartz.Trigger;
import org.quartz.TriggerListener;
import org.quartz.JobExecutionContext;
import org.quartz.listeners.TriggerListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class EmailTriggerListener extends TriggerListenerSupport {

    @Override
    public String getName() {
        return "EmailTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println("Trigger " + trigger.getKey() + " has been fired.");
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        System.err.println("Trigger " + trigger.getKey() + " misfired.");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("Trigger " + trigger.getKey() + " is complete.");
    }
}
