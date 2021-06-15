package com.tpblog.quartz.listener;

import com.tpblog.quartz.tasks.CusJob;
import org.quartz.*;
import org.quartz.impl.matchers.EverythingMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.management.MemoryUsage;

@Component
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        TriggerKey triggerKey = new TriggerKey("trigger2","group1");
        try {
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                 trigger = TriggerBuilder.newTrigger()
                        .withIdentity(triggerKey)
                        .withSchedule(CronScheduleBuilder.cronSchedule("0/5 6 13 ? * *"))
                        .build();
            }

            JobDetail detail = JobBuilder.newJob(CusJob.class)
                    .withIdentity("job2", "group1")
                    .build();

            scheduler.scheduleJob(detail,trigger);
            scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}
