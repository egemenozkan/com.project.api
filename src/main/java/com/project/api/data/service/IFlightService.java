package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.flight.Airport;

public interface IFlightService {
    List<Airport> getAirports();

    int saveAirport(Airport airport);

    void saveAirports(List<Airport> airports);
}
