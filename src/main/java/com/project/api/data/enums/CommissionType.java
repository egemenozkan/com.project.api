package com.project.api.data.enums;

public enum CommissionType {
	FIXED(1), PER_RATIO(2), NOTSET(0),;

	private final int id;

	private CommissionType(int id) {
		this.id = id;
	}

	public static CommissionType getById(int id) {
		for (CommissionType type : CommissionType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return CommissionType.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
