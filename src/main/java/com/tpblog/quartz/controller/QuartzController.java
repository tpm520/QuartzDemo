package com.tpblog.quartz.controller;

import com.tpblog.quartz.tasks.MyJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuartzController {

    @Autowired
    private Scheduler scheduler;

    private static int count = 3;
    @GetMapping("add")
    public String addTask(){
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job"+count++,"group1")
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger"+count++,"group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * *"))
                .build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }
}
