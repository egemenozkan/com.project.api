package com.project.api.data.model.place;

import com.project.api.data.enums.Language;
import com.project.api.data.enums.PlaceType;
import com.project.api.data.model.common.Address;

public class Place {
	private long id;
	private String name;
	private Language language;

	private String originalName;
	private Language originalLanguage;

	private PlaceType type;
	private Address address;
	
	private String slug;
	
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
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public Language getOriginalLanguage() {
		return originalLanguage;
	}
	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
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
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}


}
