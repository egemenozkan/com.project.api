package com.project.api.data.model.common;

public class MyResponse {
	private Boolean success = Boolean.TRUE;
	private String errorMessage;
	private Object content;
	
	public MyResponse(Boolean success, String errorMessage) {
		super();
		this.success = success;
		this.errorMessage = errorMessage;
	}
	
	public MyResponse(Boolean success) {
		super();
		this.success = success;
		this.errorMessage = null;
	}
	
	public MyResponse() {
		super();
		this.success = true;
		this.errorMessage = null;
	}
		
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
