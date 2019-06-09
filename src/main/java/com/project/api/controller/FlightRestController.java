package com.project.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.model.flight.Airport;
import com.project.api.data.service.IFlightService;

@RestController
@RequestMapping(value = "/api/v1/")
public class FlightRestController {
	// @Autowired
	// IFlightTrackingService flightTrackingService;
	@Autowired
	IFlightService flightService;

	// @RequestMapping(value = "/flights/statuses/{airportId}/{trackingType}",
	// method = RequestMethod.GET)
	// public ResponseEntity<List<FlightTrackingModel>>
	// getFlightStatus(@PathVariable int airportId, @PathVariable int trackingType)
	// {
	// List<FlightTrackingModel> flights =
	// flightTrackingService.getFlightList(airportId,
	// FlightTrackingType.getById(trackingType));
	// ResponseEntity<List<FlightTrackingModel>> response = new
	// ResponseEntity<List<FlightTrackingModel>>(flights, HttpStatus.OK);
	//
	// return response;
	// }

	@RequestMapping(value = "/airports", method = RequestMethod.GET)
	public ResponseEntity<List<Airport>> getAirports() {
		List<Airport> airports = flightService.getAirports();
		ResponseEntity<List<Airport>> response = new ResponseEntity<List<Airport>>(airports, HttpStatus.OK);

		return response;
	}
}
