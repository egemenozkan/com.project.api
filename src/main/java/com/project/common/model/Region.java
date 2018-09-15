package com.project.common.model;

public class Region {
    private int id;
    private String code;
    private String name;
    private City city;
    
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
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
    
}
