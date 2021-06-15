package com.tpblog.quartz.tasks;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TestJob {
    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("job1","group1")
                .usingJobData("jobDetail","dataDetail")
                .usingJobData("count",0)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","trigger1")
                .usingJobData("trigger","dataTrigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //for (int i=0;i<2;i++) {
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();
       // }
    }
}
