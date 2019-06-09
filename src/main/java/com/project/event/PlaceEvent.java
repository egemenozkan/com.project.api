package com.project.event;

import java.util.List;

import org.springframework.context.ApplicationEvent;

import com.project.api.data.model.common.MyPlace;

public class PlaceEvent extends ApplicationEvent {

    private static final long serialVersionUID = 4594589591081023398L;
    private List<MyPlace> places;

    public PlaceEvent(Object source, List<MyPlace> places) {
	super(source);
	this.setPlaces(places);
    }

    public List<MyPlace> getPlaces() {
	return places;
    }

    public void setPlaces(List<MyPlace> places) {
	this.places = places;
    }

}
