package com.buxiu.bootexample.service.common;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer errorcode = 0;
	private String description = "";

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(Integer errorcode) {
		this.errorcode = errorcode;
	}
	
	public BizException() {
		super();
	}

	public BizException(Integer errorcode) {
		this.errorcode = errorcode;
	}

	public BizException(Integer errorcode, String description) {
		this.errorcode = errorcode;
		this.description = description;
	}
}
