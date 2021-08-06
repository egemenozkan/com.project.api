package com.vantalii.enginee.flight.service;

import java.util.List;

import com.project.api.data.enums.FlightTrackingType;
import com.project.api.data.model.flight.FlightTrackingModel;

public interface IFlightTrackingService {

    List<FlightTrackingModel> getFlightList(int airportId, FlightTrackingType trackingType);
}
