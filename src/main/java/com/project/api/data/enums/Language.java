package com.project.api.data.enums;

public enum Language {
	ALL(1, "ALL"), TURKISH(2, "TR"), RUSSIAN(3, "RU"), ENGLISH(4, "EN"), GERMAN(5, "DE"),  NOTSET(0, "NS"),;

	private final int id;
	private final String code;

	private Language(int id, String code) {
		this.id = id;
		this.code = code;
	}

	public static Language getById(int id) {
		for (Language type : Language.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return Language.NOTSET;
	}
	
	public static Language getByCode(String code) {
		for (Language type : Language.values()) {
			if (code.equals(type.code)) {
				return type;
			}
		}
		return Language.NOTSET;
	}
	
	public int getId() {
		return id;
	}

	public String getCode() {
		return code;
	}
}
