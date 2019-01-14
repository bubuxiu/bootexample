package com.buxiu.bootexample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**  
 *  
 * 此类需要放到Application.java同包或者子包下才能被扫描，否则失效。  
 * 如果不在springboot扫描包下，可以在Application类顶部 @Import(value={SpringContextUtil.class})  
 */  

@Component
public class SpringContextUtil implements ApplicationContextAware{
     
	private static final Logger logger = LoggerFactory.getLogger(SpringContextUtil.class);  
	  
    private static ApplicationContext applicationContext;  
  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        if (SpringContextUtil.applicationContext == null) {  
        		SpringContextUtil.applicationContext = applicationContext;  
        }  
        logger.info("========ApplicationContext配置成功,,applicationContext="  
                        + SpringContextUtil.applicationContext + "========");  
    }  
  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    // 通过name获取 Bean.  
    @SuppressWarnings("unchecked")  
    public static <T> T getBean(String name) {  
        return (T) getApplicationContext().getBean(name);  
    }  
  
    // 通过class获取Bean.  
    public static <T> T getBean(Class<T> clazz) {  
        return getApplicationContext().getBean(clazz);  
    }  
  
    // 通过name,以及Clazz返回指定的Bean  
    public static <T> T getBean(String name, Class<T> clazz) {  
        return getApplicationContext().getBean(name, clazz);  
    }  
 
}