package com.project.data.elastic.entity;

import com.project.common.enums.Language;

public class Localisation {
	
	private String name;
	private Language language;
	private String slug;
	
	public Localisation(String name, Language language, String slug) {
		this.name = name;
		this.language = language;
		this.slug = slug;
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

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

}