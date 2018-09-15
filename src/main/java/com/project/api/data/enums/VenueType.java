package com.project.api.data.enums;

public enum VenueType {
	ALL(1), HOTEL(2), AIRPORT(3), BUS_TERMINAL(4), SHOPPING(5), NOTSET(0),;

	private final int id;

	private VenueType(int id) {
		this.id = id;
	}

	public static VenueType getById(int id) {
		for (VenueType type : VenueType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return VenueType.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
