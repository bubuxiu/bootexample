package com.buxiu.bootexample.service.common;

import com.buxiu.bootexample.config.SpringContextUtil;
import com.buxiu.bootexample.service.impl.TokenServiceImpl;
import com.buxiu.bootexample.utils.TimeUtils;

import java.util.Random;

public class BizCache {
    private static BizCache instance; 

    private static Random rnd;

    public BizCache() {
        super(); 
    }

    public static synchronized BizCache getInstance() {
        if (instance == null) {
            instance = new BizCache();
            rnd = new Random();
        }
        return instance;
    }
  
    public Random getRnd() {
        return rnd;
    }

    //获取符合SQL格式的当前时间，YYYY-MM-DD HH24:mm:ss格式
    public String getNow() {
        return TimeUtils.formatDate(TimeUtils.DB_TIME_PATTERN_F2);
    } 
    //获取当天日期YYYY-MM-DD格式
    public String getToday() {
        return TimeUtils.formatDate(TimeUtils.DB_TIME_PATTERN_F2).substring(0, 10);
    }

    // 获取当前登录用户ID
    public int getUserid() {
        TokenServiceImpl userServ = SpringContextUtil.getBean("tokenService", TokenServiceImpl.class);
        return userServ.getUserId();
    }

    public String getLoginip() {
        TokenServiceImpl userServ = SpringContextUtil.getBean("tokenService", TokenServiceImpl.class);
        return userServ.getRemoteIp();
    }

    // 获取当前登录用户名
    public String getUserName() {
        TokenServiceImpl userServ = SpringContextUtil.getBean("tokenService",
                TokenServiceImpl.class);
        return userServ.getUserName();
    }

    // 获取当前登录用户类型
    public Integer getUserType() {
        TokenServiceImpl userServ = SpringContextUtil.getBean("tokenService", TokenServiceImpl.class);
        return userServ.getUserType();
    }
 
}
