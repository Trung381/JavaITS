package com.example.vuvantrung.config;

import com.example.vuvantrung.scheduler.EmailJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail emailJobDetail() {
        return JobBuilder.newJob(EmailJob.class)
                .withIdentity("EmailJob")  // Đặt tên cho job
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(emailJobDetail())
                .withIdentity("sampleTrigger")  // Đặt tên cho trigger
                .startNow()
//                .endAt()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/59 * * * * ?"))
                .build();
                //                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                //                        .withIntervalInSeconds(5)  // Lặp lại mỗi 15 phút
                ////                        .repeatForever()
                //                                .withRepeatCount(10)
                //                )
    }

}
