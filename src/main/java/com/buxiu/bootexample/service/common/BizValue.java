package com.buxiu.bootexample.service.common;

public class BizValue {
	public final static int SYSTEM_DEFAULT_KEY_START = 1000;	
	
	// 用户相关
	public final static String INIT_PASSWORD = "111111";
	public final static int CHECKCODE_VALIDTIME = 5;
		
	// 超级管理员
	public final static int USERID_ADMIN = 1; // 超级用户
	public final static int ROLEID_ADMIN = 1;
	
	//后台用户用户类型
	public final static int USERTYPE_ADMIN = 1;
	
	// 账号停用、激活状态
	public final static int USER_STATUS_NORMAL = 1;
	public final static int USER_STATUS_BLOCK = 2;
	// 用户登录登出状态
	public final static int USER_LOGIN_NO = 0;
	public final static int USER_LOGIN_YES = 1;
		 
	
	/**
	 * 组织名称, token算法需要
	 */
	public static final String GROUP_NAME = "xxx";
}
