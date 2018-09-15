package com.project.api.data.enums;

public enum Gender {
    FEMALE(1), MALE(2), NOTSET(0);

    private final int id;

    private Gender(int id) {
	this.id = id;
    }

    public static Gender getById(int id) {
	for (Gender gender : Gender.values()) {
	    if (gender.id == id) {
		return gender;
	    }
	}
	return Gender.NOTSET;
    }

    public int getId() {
	return this.id;
    }
}
