package com.project.api.data.enums;

public enum CompanyType {
    TRANSFER(1), AGENCY(2), TOUROPERATOR(3), NOTSET(0), ALL(-1);

    private final int id;

    private CompanyType(int id) {
	this.id = id;
    }

    public static CompanyType getById(int id) {
	for (CompanyType type : CompanyType.values()) {
	    if (type.id == id) {
		return type;
	    }
	}
	return CompanyType.NOTSET;
    }
    
    public int getId() {
	return this.id;
}
}
