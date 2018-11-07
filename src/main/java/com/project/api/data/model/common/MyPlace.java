package com.project.api.data.model.common;

import org.locationtech.spatial4j.shape.Point;

public class MyPlace {
    private long id;
    private String fbPlaceId;
    private String name;
    private Point coordinate;

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public String getFbPlaceId() {
	return fbPlaceId;
    }

    public void setFbPlaceId(String fbPlaceId) {
	this.fbPlaceId = fbPlaceId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Point getCoordinate() {
	return coordinate;
    }

    public void setCoordinate(Point coordinate) {
	this.coordinate = coordinate;
    }


}
