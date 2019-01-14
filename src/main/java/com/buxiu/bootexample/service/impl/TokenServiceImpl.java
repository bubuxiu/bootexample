/**
 * 
 */
package com.buxiu.bootexample.service.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.buxiu.bootexample.service.ITokenUserService;

import java.util.Date;

/**
 * 当前用户Service, 发起服务请求的用户信息, 从令牌中获取, 生命周期同请求request
 */
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service("tokenService")
public class TokenServiceImpl implements ITokenUserService {
	/**
	 * 用户Id
	 */
	private Integer userId;
	
	/**
	 * 用户类型
	 */
	private int userType;

	/**
	 * 用户类型
	 */
	private String userName;
	
	/**
	 * 令牌
	 */
	private String token;

    /**
     * 令牌过期时间
     */
	private Date expiration;

	/**
	 * 远程调用端IP
	 */
	private String remoteIp;
	
	@Override
    public String getUserName() {
		return userName;
	}
	
    @Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
    public String getToken() {
        return token;
    }

    @Override
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Date getExpiration() {
        return expiration;
    }

    @Override
    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

	@Override
	public String getRemoteIp() {
		return remoteIp;
	}

	@Override
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	@Override
	public Integer getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public Integer getUserType() {
		return this.userType;
	}

	@Override
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
