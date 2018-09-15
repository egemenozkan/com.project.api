package com.project.api.data.service;

import java.util.List;

import com.project.api.data.enums.VenueType;
import com.project.api.data.model.common.Venue;

public interface IVenueService {
    int saveVenue(Venue venue);
    Venue getVenueById(int venueId);
    List<Venue> getVenuesByType(VenueType venueType);
    void syncVenue(VenueType venueType, int selfId);
}
