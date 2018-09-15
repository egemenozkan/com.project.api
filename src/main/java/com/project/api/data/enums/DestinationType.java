package com.project.api.data.enums;

public enum DestinationType {
	MAIN_REGION(1), REGION(2), SUBREGION(3), VENUE(4),  NOTSET(0),;

	private final int id;

	private DestinationType(int id) {
		this.id = id;
	}

	public static DestinationType getById(int id) {
		for (DestinationType type : DestinationType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return DestinationType.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
