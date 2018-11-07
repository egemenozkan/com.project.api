package com.project.api.data.model.place;

import com.project.api.data.enums.PlaceType;
import com.project.api.data.model.common.Address;

public class Place {
	private long id;
	private String name;
	private PlaceType type;
	private Address address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlaceType getType() {
		return type;
	}

	public void setType(PlaceType type) {
		this.type = type;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
