package com.project.api.data.model.common;

import com.project.api.data.enums.VenueType;

public class Venue {
    private int id;
    private String name;
    private VenueType type;
    private Address address;
    
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
    public VenueType getType() {
        return type;
    }
    public void setType(VenueType type) {
        this.type = type;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    
    
    
 
    
}
