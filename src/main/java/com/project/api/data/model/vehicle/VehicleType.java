package com.project.api.data.model.vehicle;

public enum VehicleType {
	SEDAN(1), SUV(2), PICKUP(3), COUPE(4), MINIVAN(5), WAGON(6), HATCHBACK(7), CABRIOLET(8), VAN(9), MIDIBUS(10), MINIBUS(11), BUS(12), LIMOUSINE(13), NOTSET(0);

	private final int id;

	private VehicleType(int id) {
		this.id = id;
	}

	public static VehicleType getById(int id) {
		for (VehicleType type : VehicleType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return VehicleType.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
