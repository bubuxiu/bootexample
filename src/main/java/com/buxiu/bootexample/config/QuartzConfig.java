package com.buxiu.bootexample.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.buxiu.bootexample.task.MyJobFactory;
import com.buxiu.bootexample.task.MyScheduler;

@Configuration
public class QuartzConfig implements ApplicationListener<ContextRefreshedEvent> { 
	
    @Autowired
    public MyScheduler myScheduler;
    
    @Autowired
    private MyJobFactory myJobFactory;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) { 
        try { 
            myScheduler.scheduleJobs(); 
        } catch (SchedulerException e) { 
            e.printStackTrace(); 
        }
     } 
    
    @Bean(name="mySchedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factory = new SchedulerFactoryBean(); 
        //factory.setOverwriteExistingJobs(true);

        // 延时启动
        factory.setStartupDelay(2);

        // 加载quartz数据源配置
        //factory.setQuartzProperties(quartzProperties());

        // 自定义Job Factory，用于Spring注入
        factory.setJobFactory(myJobFactory);

        return factory;
    }
    
    @Bean(name = "scheduler") 
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
 
