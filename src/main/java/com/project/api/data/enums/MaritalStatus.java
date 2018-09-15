package com.project.api.data.enums;

public enum MaritalStatus {
	SINGLE(1), MARRIED(2), DIVORCED(3), NOTSET(0);

	private final int id;

	private MaritalStatus(int id) {
		this.id = id;
	}

	public static MaritalStatus getById(int id) {
		for (MaritalStatus type : MaritalStatus.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return MaritalStatus.NOTSET;
	}

	public int getId() {
		return this.id;
	}
}
