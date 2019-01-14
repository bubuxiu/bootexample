package com.buxiu.bootexample.po.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseBean {
	
	private String sortord; /* 排序方式 */
	
	private String term; /* 模糊条件 */
	@JsonIgnore
	public String getSortord() {
		return sortord;
	}
	@JsonProperty
	public void setSortord(String sortord) {
		this.sortord = sortord;
	}
	@JsonIgnore
	public String getTerm() {
		return term;
	}
	@JsonProperty
	public void setTerm(String term) {
		this.term = term;
	}
}
