package com.project.common.model;

public class Country {
    private int id;
    private String code;
    private String name;
    private String phoneCode;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getPhoneCode() {
	return phoneCode;
    }
    public void setPhoneCode(String phoneCode) {
	this.phoneCode = phoneCode;
    }
    
    
}
