package com.project.api.data.enums;

public enum EmailType {
	PRIVATE(1), WORK(2), UNIVERSITY(3), NOTSET(0);

	private final int id;

	private EmailType(int id) {
		this.id = id;
	}

	public static EmailType getById(int id) {
		for (EmailType type : EmailType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return EmailType.NOTSET;
	}
	
	public int getId() {
	    return this.id;
	}
}
