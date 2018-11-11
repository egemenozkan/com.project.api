package com.project.api.data.enums;

public enum Language {
	ALL(1), TURKISH(2), RUSSIAN(3), ENGLISH(4), GERMAN(5),  NOTSET(0),;

	private final int id;

	private Language(int id) {
		this.id = id;
	}

	public static Language getById(int id) {
		for (Language type : Language.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return Language.NOTSET;
	}
	
	public int getId() {
		return id;
	}
}
