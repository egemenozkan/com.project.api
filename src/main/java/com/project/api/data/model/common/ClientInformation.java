package com.project.api.data.model.common;

import java.time.LocalDateTime;

public class ClientInformation {
	private int userId;
	private int applicatonId;

	private String ip;
	private LocalDateTime loginDateTime;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public LocalDateTime getLoginDateTime() {
		return loginDateTime;
	}
	public void setLoginDateTime(LocalDateTime loginDateTime) {
		this.loginDateTime = loginDateTime;
	}
	public int getApplicatonId() {
		return applicatonId;
	}
	public void setApplicatonId(int applicatonId) {
		this.applicatonId = applicatonId;
	}
	
	
}
