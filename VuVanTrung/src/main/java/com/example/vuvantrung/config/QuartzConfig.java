package com.example.vuvantrung.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {

    // Tạo JobDetail cho SampleJob
    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder.newJob(SampleJob.class)
                .withIdentity("sampleJob")  // Đặt tên cho job
                .storeDurably()
                .build();
    }

    // Tạo Trigger cho SampleJob với thời gian biểu cụ thể
    @Bean
    public Trigger sampleJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(sampleJobDetail())
                .withIdentity("sampleTrigger")  // Đặt tên cho trigger
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))  // Chạy mỗi 5 phút
                .build();
    }
}
