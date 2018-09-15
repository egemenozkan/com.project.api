package com.project.api.data.model.spatial;

import com.project.api.data.model.common.IdValue;

/* 
 * @param Pointo */

public class Pointo {
	private Long id;
	private String point;
//	private Coordinate coordinate;
	private IdValue pointCategory;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
//	public Coordinate getCoordinate() {
//		return coordinate;
//	}
//	public void setCoordinate(Coordinate coordinate) {
//		this.coordinate = coordinate;
//	}
	public IdValue getPointCategory() {
		return pointCategory;
	}
	public void setPointCategory(IdValue pointCategory) {
		this.pointCategory = pointCategory;
	}
	
}
