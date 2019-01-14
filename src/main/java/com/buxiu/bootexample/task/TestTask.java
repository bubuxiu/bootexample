package com.buxiu.bootexample.task;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;


// 可以继承QuartzJobBean 或Job 或StatefulJob，有差别

@DisallowConcurrentExecution
@Service
public class TestTask extends QuartzJobBean {
//	@Autowired
//  private UserServiceImpl userService;
	
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("test任务开始");
        
        try {
        		//UserPo u = userService.getUser(1);
        		//System.out.println(u);
        	
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("test任务结束");
    }

}
