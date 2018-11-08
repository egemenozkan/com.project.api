package com.project.api.data.model.common;

import com.project.api.data.enums.AddressType;

public class Address {
	private Long id;
	private AddressType type;
	private String addressTitle;
	private String addressLine1;
	private String addressLine2;
	private String description;
	private String postCode;
	private int priority;
	private Double lat;
	private Double lng;
	private int subregionId;
	private String subregion;
	private int regionId;
	private String region;
	private int cityId;
	private String city;
	private int countryId;
	private String country;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public AddressType getType() {
		return type;
	}
	public void setType(AddressType type) {
		this.type = type;
	}
	public String getAddressTitle() {
		return addressTitle;
	}
	public void setAddressTitle(String addressTitle) {
		this.addressTitle = addressTitle;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public int getSubregionId() {
		return subregionId;
	}
	public void setSubregionId(int subregionId) {
		this.subregionId = subregionId;
	}
	public String getSubregion() {
		return subregion;
	}
	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
	
}
