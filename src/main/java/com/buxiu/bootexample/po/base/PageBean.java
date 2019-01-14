package com.buxiu.bootexample.po.base;
 

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PageBean extends BaseBean {
	//@JsonIgnore
	private Integer pageNum;// sql语句的 limit的开始，从1开始
	//@JsonIgnore
	private Integer pageSize; 
	
	public PageBean() {
		pageNum = 1;
		pageSize = 10; 
	}
	@JsonIgnore
	public Integer getPageNum() {
		return pageNum;
	} 
	@JsonProperty
	public void setPageNum(Integer pagenum) {
		this.pageNum = pagenum;
	}
	@JsonIgnore
	public Integer getPageSize() {
		return pageSize;
	}
	@JsonProperty
	public void setPageSize(Integer pagesize) {
		this.pageSize = pagesize;
	}
	 
}

