package com.project.api.data.enums;

public enum CityType {
	IL(1), ILCE(2), BELDE(3), NOTSET(0);
	
	private final int id;

    private CityType(int id) {
        this.id = id;
    }
    
    public static CityType getById(int id) {
    	for (CityType gender : CityType.values()) {
           if (gender.id == id) {
        	  return gender;
           }
        }
    	return CityType.NOTSET;
    }
}
