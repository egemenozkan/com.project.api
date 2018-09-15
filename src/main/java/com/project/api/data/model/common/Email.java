package com.project.api.data.model.common;

import com.project.api.data.enums.EmailType;

public class Email {
	private int id;
	private EmailType type;
	private String email;
	private int priority;
	private boolean authentication;
	
	public int getId() {
	    return id;
	}
	
	public void setId(int id) {
	    this.id = id;
	}
	
	public EmailType getType() {
		return type;
	}
	
	public void setType(EmailType type) {
		this.type = type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public boolean isAuthentication() {
	    return authentication;
	}

	public void setAuthentication(boolean authentication) {
	    this.authentication = authentication;
	}

	@Override
	public boolean equals(Object o){
	  if(o instanceof Email){
	    Email toCompare = (Email) o;
	    return this.id == toCompare.id;
	  }
	  return false;
	}
}
