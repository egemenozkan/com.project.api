package com.project.api.data.model.common;

import com.project.api.data.enums.DestinationType;
import com.project.api.data.enums.RegionType;
import com.project.api.data.enums.VenueType;

public class DestinationAutocomplete {
    private int selfId;
    private String name;
    private VenueType venueType;
    private RegionType regionType;
    private DestinationType destinationType;
    
    public int getSelfId() {
        return selfId;
    }
    public void setSelfId(int selfId) {
        this.selfId = selfId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public VenueType getVenueType() {
        return venueType;
    }
    public void setVenueType(VenueType venueType) {
        this.venueType = venueType;
    }
    public RegionType getRegionType() {
        return regionType;
    }
    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }
    public DestinationType getDestinationType() {
        return destinationType;
    }
    public void setDestinationType(DestinationType destinationType) {
        this.destinationType = destinationType;
    }
    
    
    
}
