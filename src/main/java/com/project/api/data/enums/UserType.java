package com.project.api.data.enums;

public enum UserType {
	ALL(1), AGENCY_USER(2), TRANSPORTER_USER(3), DRIVER(4), HOTEL_USER(5), B2C_USER(6), ADMIN_USER(7), NOTSET(0);

	private final int id;

	private UserType(int id) {
		this.id = id;
	}

	public static UserType getById(int id) {
		for (UserType type : UserType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return UserType.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
