package com.project.api.data.model.common;

public class DBClient {
	private Long userId = 0L;
	private int companyId = 0;
	private String ip = "";
	private int projectId = 0;
	private int applicationId = 0;
	private int extraId = 0;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public int getExtraId() {
		return extraId;
	}
	public void setExtraId(int extraId) {
		this.extraId = extraId;
	}
	
	
	
	
	
}
