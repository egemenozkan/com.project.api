package com.project.api.data.model.driver;

public enum DriverProsthesis {
	GLASS(1), LENS(2), GLASSORlENS(3), WINKER(4), PERSONALOPTICDEVICE(5), HEARINGSUPPORT(6), LIMBPROSTHESIS(7),
	HANDLEPROSTHESIS(8), LEGPROSTHESIS(9), NOTSET(0);

	private final int id;

	private DriverProsthesis(int id) {
		this.id = id;
	}

	public static DriverProsthesis getById(int id) {
		for (DriverProsthesis type : DriverProsthesis.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return DriverProsthesis.NOTSET;
	}
}
