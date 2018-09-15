package com.project.api.data.enums;

public enum RegionType {
	MAIN_REGION(1), REGION(2), SUBREGION(3), NOTSET(0);

	private final int id;

	private RegionType(int id) {
		this.id = id;
	}

	public static RegionType getById(int id) {
		for (RegionType type : RegionType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return RegionType.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
