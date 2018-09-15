package com.project.api.data.model.driver;

public enum ReasonOfLeavingType {
	NOTSET(0);

	private final int id;

	private ReasonOfLeavingType(int id) {
		this.id = id;
	}

	public static ReasonOfLeavingType getById(int id) {
		for (ReasonOfLeavingType type : ReasonOfLeavingType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return ReasonOfLeavingType.NOTSET;
	}
}
