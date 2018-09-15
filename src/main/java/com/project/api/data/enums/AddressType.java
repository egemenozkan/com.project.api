package com.project.api.data.enums;

public enum AddressType {
	HOME(1), WORK(2), TEMPORARY(3), NOTSET(0);
	
	private final int id;
    private AddressType(int id) {
        this.id = id;
    }
    
    public static AddressType getById(int id) {
	    	for (AddressType type : AddressType.values()) {
	           if (type.id == id) {
	        	  return type;
	           }
	        }
	    	return AddressType.NOTSET;
    }
}
