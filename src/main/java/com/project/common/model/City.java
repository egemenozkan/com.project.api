package com.project.common.model;

public class City {
    private int id;
    private String code;
    private String name;
    private String phoneCode;
    private String plateCode;
    private Country country;
    
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
    
    public String getPlateCode() {
        return plateCode;
    }
    public void setPlateCode(String plateCode) {
        this.plateCode = plateCode;
    }
    public Country getCountry() {
        return country;
    }
    public void setCountry(Country country) {
        this.country = country;
    }
    
    
}
