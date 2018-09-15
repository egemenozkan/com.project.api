package com.project.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.enums.VenueType;
import com.project.api.data.model.common.Venue;
import com.project.api.data.model.flight.Airport;
import com.project.api.data.service.IFlightService;
import com.project.api.data.service.IVenueService;

@RestController
@RequestMapping(value = "/api/v1/")
public class VenueRestController {
    @Autowired
    IVenueService venueService;
    @Autowired
    IFlightService flightService;
//    @Autowired
//    IHotelService hotelService;

    @RequestMapping(value = "/venues/type/{venueType}", method = RequestMethod.GET)
    public ResponseEntity<List> getVenuesByType(@PathVariable int venueType) {
	List<Venue> venues = venueService.getVenuesByType(VenueType.getById(venueType));
	ResponseEntity<List> response = new ResponseEntity<>(venues, HttpStatus.OK);
	return response;
    }

    @RequestMapping(value = "/venues/{id}", method = RequestMethod.GET)
    public ResponseEntity<Venue> getVenueById(@PathVariable int id) {
	Venue venue = venueService.getVenueById(id);
	ResponseEntity<Venue> response = new ResponseEntity<Venue>(venue, HttpStatus.OK);
	return response;
    }

    @RequestMapping(value = "/venues/save", method = RequestMethod.POST)
    public ResponseEntity<Integer> saveCompany(RequestEntity<Venue> requestEntity) {
	Venue venue = requestEntity.getBody();
	int venueId = venueService.saveVenue(venue);
	ResponseEntity<Integer> response = new ResponseEntity<Integer>(venueId, HttpStatus.OK);

	return response;
    }
    
    @RequestMapping(value = "/venues/sync", method = RequestMethod.GET)
    public ResponseEntity<Integer> syncVenues() {
	List<Airport> airports = flightService.getAirports();
	for (Airport airport : airports) {
	    venueService.syncVenue(VenueType.AIRPORT, airport.getId());
	}
//	List<Hotel> hotels = hotelService.getHotelsByFilter(0, 0, 0);
//	for (Hotel hotel : hotels) {
//	    venueService.syncVenue(VenueType.HOTEL, hotel.getId());
//	}
//	
	ResponseEntity<Integer> response = new ResponseEntity<Integer>(1, HttpStatus.OK);
	return response;
    }

}