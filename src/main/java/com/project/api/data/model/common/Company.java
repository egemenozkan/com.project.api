package com.project.api.data.model.common;

import com.project.api.data.enums.CompanyType;

public class Company {
	private int id;
	private String name;
	private CompanyType type;
	private ContactInformation contactInformation;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CompanyType getType() {
	    return type;
	}

	public void setType(CompanyType type) {
	    this.type = type;
	}

	public ContactInformation getContactInformation() {
	    return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
	    this.contactInformation = contactInformation;
	}
	
	
}
