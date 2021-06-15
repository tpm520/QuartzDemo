package com.tpblog.quartz.tasks;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String format = simpleDateFormat.format(new Date());
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();
        JobDataMap detailDataMap = jobDetail.getJobDataMap();
        JobDataMap triggerDataMap = trigger.getJobDataMap();
        String jobDetail1 = detailDataMap.getString("jobDetail");
        String trigger1 = triggerDataMap.getString("trigger");
        int count = detailDataMap.getInt("count");
        detailDataMap.put("count",count+1);
        System.out.println("count==="+count);
        /*try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println("任务执行--"+format);
        System.out.println("jobDetail===="+jobDetail1);
        System.out.println("trigger===="+trigger1);
    }
}
