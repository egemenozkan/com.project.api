package com.project.api.data.model.flight;

import com.project.api.data.model.common.Location;

public class Airport {
	private int id;
	private String iata;
	private String name;
	private int venueId;
	private String icao;
	private String faa;
	private int dhmId; 
	private Location location;
	private boolean internationalFlights;
	private String slug;
	
	public int getId() {
	    return id;
	}
	public void setId(int id) {
	    this.id = id;
	}
	public String getIata() {
	    return iata;
	}
	public void setIata(String iata) {
	    this.iata = iata;
	}
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
	public int getVenueId() {
	    return venueId;
	}
	public void setVenueId(int venueId) {
	    this.venueId = venueId;
	}
	public String getIcao() {
	    return icao;
	}
	public void setIcao(String icao) {
	    this.icao = icao;
	}
	public String getFaa() {
	    return faa;
	}
	public void setFaa(String faa) {
	    this.faa = faa;
	}
	public int getDhmId() {
	    return dhmId;
	}
	public void setDhmId(int dhmId) {
	    this.dhmId = dhmId;
	}
	public Location getLocation() {
	    return location;
	}
	public void setLocation(Location location) {
	    this.location = location;
	}
	public boolean isInternationalFlights() {
	    return internationalFlights;
	}
	public void setInternationalFlights(boolean internationalFlights) {
	    this.internationalFlights = internationalFlights;
	}
	public String getSlug() {
	    return slug;
	}
	public void setSlug(String slug) {
	    this.slug = slug;
	}
	
}
