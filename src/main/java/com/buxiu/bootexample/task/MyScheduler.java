package com.buxiu.bootexample.task;

import org.springframework.stereotype.Component;
import org.quartz.*;
//import org.quartz.impl.StdScheduler;
//import org.springframework.context.ApplicationContext; 
//import com.buxiu.bootexample.config.SpringContextUtil;

@Component
public class MyScheduler {
//    @Autowired
//    private Scheduler scheduler;
//    @SuppressWarnings("unused")
    public void scheduleJobs() throws SchedulerException {
        //ApplicationContext ctx = SpringContextUtil.getApplicationContext();
        
        //Scheduler scheduler = (StdScheduler) ctx.getBean("mySchedulerFactoryBean");//获得上面创建的bean

        //startTestJob(scheduler);
        //scheduler.start();
    }

    // 注册job
    @SuppressWarnings("unused")
    private void startTestJob(Scheduler scheduler) throws SchedulerException {
        JobDetail job = JobBuilder.newJob(TestTask.class).withIdentity("testTask").storeDurably().build();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("testTrigger").withSchedule(scheduleBuilder).build();

        scheduler.scheduleJob(job, cronTrigger);
    }
}

 