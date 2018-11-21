package com.project.api.data.enums;

public enum Star {
	ALL(1), STAR_3(3), STAR_4(5), STAR_5(5),  NOTSET(0);

	private final int id;

	private Star(int id) {
		this.id = id;
	}

	public static Star getById(int id) {
		for (Star type : Star.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return Star.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
