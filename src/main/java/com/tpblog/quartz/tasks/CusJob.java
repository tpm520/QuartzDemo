package com.tpblog.quartz.tasks;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.core.jmx.QuartzSchedulerMBean;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CusJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("CusJob "+new SimpleDateFormat("YYYY-MM-dd HH:mm:ss").format(new Date()) +context.getScheduler());
    }
}
