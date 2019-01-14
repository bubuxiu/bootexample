package com.buxiu.bootexample.service;

import java.util.Date;

/**
 * 当前用户Service, 发起服务请求的用户信息, 生命周期同请求request 
 */
public interface ITokenUserService {

	/**
	 * 获取用户Id
	 * @return 用户Id
	 */
	Integer getUserId();

	/**
	 * 设置用户Id
	 * @param accountId 用户Id
	 */
	void setUserId(Integer userId);
	
	// 登录用户的用户名 
	public String getUserName() ;
	

	public void setUserName(String userName) ;
	/**
	 * 取得用户类型
	 * @return 用户类型
	 */
	Integer getUserType();
	
	/**
	 * 设置用户类型
	 * @param userType 用户类型
	 */
	void setUserType(Integer userType);

	/**
	 * 获取客户端令牌
	 * @return 令牌
	 */
	String getToken();

	/**
	 * 设置客户端令牌
	 * @param token 令牌
	 */
	void setToken(String token);

	/**
	 * 获取客户端令牌过期时间
	 * @return 令牌过期时间
	 */
	Date getExpiration();

	/**
	 * 设置客户端令牌过期时间
	 * @param expiration 令牌过期时间
	 */
	void setExpiration(Date expiration);

	/**
	 * 获取远程调用客户端IP地址
	 * @return
	 */
	String getRemoteIp();

	/**
	 * 设置客户端IP地址
	 */
	void setRemoteIp(String remoteIp);
}
