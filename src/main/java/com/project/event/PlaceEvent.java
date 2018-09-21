package com.project.event;

import org.springframework.context.ApplicationEvent;

public class PlaceEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4594589591081023398L;
	private String name;
	
	public PlaceEvent(Object source, String name) {
		super(source);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
