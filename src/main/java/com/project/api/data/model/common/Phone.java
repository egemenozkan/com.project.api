package com.project.api.data.model.common;

import com.project.api.data.enums.PhoneType;
import com.project.common.model.Country;

public class Phone {
	private int id;
	private PhoneType type;
	private Country country;
	private String number;
	private int priority;
	private boolean authentication;

	
	public int getId() {
	    return id;
	}
	public void setId(int id) {
	    this.id = id;
	}
	public PhoneType getType() {
		return type;
	}
	public void setType(PhoneType type) {
		this.type = type;
	}

	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	  if(o instanceof Phone){
	    Phone toCompare = (Phone) o;
	    return this.id == toCompare.getId();
	  }
	  return false;
	}
	
}
