package com.project.api.data.model.hotel;

import com.project.api.data.enums.Star;
import com.project.api.data.model.place.Place;

public class Hotel extends Place {
	private String formerName;
	private Star star;

	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	public Star getStar() {
		return star;
	}

	public void setStar(Star star) {
		this.star = star;
	}
}
