package com.project.api.data.enums;

public enum BloodType {
	RHP0(1), RHN0(2), RHPA(3), RHNA(4), RHPB(5), RHNB(6), RHPAB(7), RHNAB(8), NOTSET(0);

	private final int id;

	private BloodType(int id) {
		this.id = id;
	}

	public static BloodType getById(int id) {
		for (BloodType type : BloodType.values()) {
			if (type.id == id) {
				return type;
			}
		}
		return BloodType.NOTSET;
	}

	public int getId() {
		return this.id;
	}
}
