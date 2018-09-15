package com.project.api.data.enums;

public enum PhoneType {
	HOME(1), WORK(2), MOBILE(3), NOTSET(0);
	
	private final int id;
    private PhoneType(int id) {
        this.id = id;
    }
    
    public static PhoneType getById(int id) {
	    	for (PhoneType type : PhoneType.values()) {
	           if (type.id == id) {
	        	  return type;
	           }
	        }
	    	return PhoneType.NOTSET;
    }
    
    public int getId() {
	return this.id;
    }
}
