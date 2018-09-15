package com.project.api.data.enums;

public enum FlightTrackingType {
    INTERNATIONAL_ARRIVAL(1), INTERNATIONAL_DEPARTURE(2), DOMESTIC_ARRIVAL(3), DOMESTIC_DEPARTURE(4), NOTSET(0);

    private final int id;

    private FlightTrackingType(int id) {
	this.id = id;
    }

    public static FlightTrackingType getById(int id) {
	for (FlightTrackingType type : FlightTrackingType.values()) {
	    if (type.id == id) {
		return type;
	    }
	}
	return FlightTrackingType.NOTSET;
    }

    public int getId() {
	return this.id;
    }
}
