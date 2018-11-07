package com.project.api.data.enums;

public enum PlaceType {
	ALL(1), HOTEL_LODGING(2), AIRPORT(3), BUS_TERMINAL(4), SHOPPING(5), RESTAURANT_CAFE(6), SIGHT_LANDMARK(7), NOTSET(0),;

	private final int id;

	private PlaceType(int id) {
		this.id = id;
	}

	public static PlaceType getById(int id) {
		for (PlaceType type : PlaceType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return PlaceType.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
