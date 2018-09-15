package com.project.api.data.model.common;

import java.io.Serializable;

public class IdValue implements Serializable {
	private int id;
	private String value;
	private String code;
	private Boolean active;

	public IdValue() {
		super();
	};
	
	public IdValue(int id, String value) {
		super();
		this.id = id;
		this.value = value;
	}
	
	public IdValue(int id, String value, String code) {
		super();
		this.id = id;
		this.value = value;
		this.code = code;
	}
	public IdValue(int id, String value, Boolean active) {
		super();
		this.id = id;
		this.value = value;
		this.active = active;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
