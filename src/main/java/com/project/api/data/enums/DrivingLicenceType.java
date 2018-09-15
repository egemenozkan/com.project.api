package com.project.api.data.enums;

public enum DrivingLicenceType {
	B1(1), B(2), C1(3), C(4), D1(5), D(6), DE(7), NOTSET(0);
	
	private final int id;

    private DrivingLicenceType(int id) {
        this.id = id;
    }
    
    public static DrivingLicenceType getById(int id) {
    	for (DrivingLicenceType type : DrivingLicenceType.values()) {
           if (type.id == id) {
        	  return type;
           }
        }
    	return DrivingLicenceType.NOTSET;
    }
    
	public int getId() {
		return id;
	}
}
