package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.driver.Driver;
import com.project.api.data.model.vehicle.VehicleDriverAssigment;

public interface IDriverService {
	Long saveDriverProfile(Driver driver);
	Driver getDriverProfileByDriverId(Long driverId);
	Driver getDriverByDriverId(Long driverId);
	List<Driver> getCompanyDriverProfileList(int companyId);
	List<VehicleDriverAssigment> getVehicleDriverAssignments(int driverId);
	VehicleDriverAssigment saveVehicleDriverAssignment(VehicleDriverAssigment assignment);
	VehicleDriverAssigment saveVehicleDriverAssignmentUnassign(VehicleDriverAssigment assignment);

	
}
