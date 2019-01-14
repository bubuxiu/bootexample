package com.buxiu.bootexample.po;

import java.io.Serializable;
import com.buxiu.bootexample.po.base.PageBean;

/**
*
*  数据库表：用户表
*
*  @Author bubuxiu@gmail.com
*
**/

public class UserPo extends PageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 自增加主键;NOTNULL;PRIMARYKEY */
	private Integer userid;
	/** 邮箱,检索条件;MAXLENGTH(128) */
	private String email;
	/** 姓名,检索条件;MAXLENGTH(64) */
	private String username;
	/** 性别，1男，2女 */
	private Integer gender;
	/** 登录密码;NOTNULL;MAXLENGTH(32) */
	private String password;
	/** 手机号,检索条件;MAXLENGTH(30) */
	private String mobileno;
	/** 1未激活，2激活（正常），3停用（冻结） */
	private Integer status;
	/** 注册时间;NOTNULL;MAXLENGTH(30) */
	private String registertime;
	/** 登录时间;MAXLENGTH(30) */
	private String logintime;
	/** 登录来源;MAXLENGTH(128) */
	private String loginip;
	/** 登录状态，0未登录，1登录 */
	private Integer loginstatus;


	// 数据库表字段之外的属性定义从这里开始，请遵循注释规范


	public Integer getUserid(){
		return userid;
	}
	public void setUserid(Integer userid){
		 this.userid=userid;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		 this.email=email;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		 this.username=username;
	}
	public Integer getGender(){
		return gender;
	}
	public void setGender(Integer gender){
		 this.gender=gender;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		 this.password=password;
	}
	public String getMobileno(){
		return mobileno;
	}
	public void setMobileno(String mobileno){
		 this.mobileno=mobileno;
	}
	public Integer getStatus(){
		return status;
	}
	public void setStatus(Integer status){
		 this.status=status;
	}
	public String getRegistertime(){
		return registertime;
	}
	public void setRegistertime(String registertime){
		 this.registertime=registertime;
	}
	public String getLogintime(){
		return logintime;
	}
	public void setLogintime(String logintime){
		 this.logintime=logintime;
	}
	public String getLoginip(){
		return loginip;
	}
	public void setLoginip(String loginip){
		 this.loginip=loginip;
	}
	public Integer getLoginstatus(){
		return loginstatus;
	}
	public void setLoginstatus(Integer loginstatus){
		 this.loginstatus=loginstatus;
	}

}
