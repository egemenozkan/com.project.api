package com.project.api.data.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.api.data.model.driver.Driver;
import com.project.api.data.model.vehicle.VehicleDriverAssigment;
import com.project.api.data.service.IDriverService;
import com.project.common.BaseService;

@SuppressWarnings("unchecked")
@Service
public class DriverService extends BaseService implements IDriverService {

    @Override
    public Long saveDriverProfile(Driver driver) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Driver getDriverProfileByDriverId(Long driverId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Driver getDriverByDriverId(Long driverId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Driver> getCompanyDriverProfileList(int companyId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<VehicleDriverAssigment> getVehicleDriverAssignments(int driverId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public VehicleDriverAssigment saveVehicleDriverAssignment(VehicleDriverAssigment assignment) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public VehicleDriverAssigment saveVehicleDriverAssignmentUnassign(VehicleDriverAssigment assignment) {
	// TODO Auto-generated method stub
	return null;
    }

//	@Override
//	public Long saveDriverProfile(Driver driver) {
//		Long result = this.callLong("PKG_DRIVER__P_SAVE_DRIVER_PROFILE",
//			driver.getId(), driver.getFirstName(), driver.getMiddleName(), driver.getLastName(),
//			driver.getBirthdate(), 
//			driver.getGender() != null ? driver.getGender().getId() : Gender.NOTSET,
//			driver.getMaritalStatus() != null ? driver.getMaritalStatus().getId() : MaritalStatus.NOTSET,
//			driver.getBloodType() != null ? driver.getBloodType().getId() : BloodType.NOTSET,
//			driver.getNationality() != null ? driver.getNationality().getId() : 0, driver.getNationalID());
//		
//		return result;
//	}
//
//	@Override
//	public Driver getDriverProfileByDriverId(Long driverId) {
//		Driver result = (Driver) this.callRow("PKG_DRIVER__P_GET_DRIVER_PROFILE_BY_DRIVER_ID_1",
//			new DriverRowMapper(),
//			driverId);
//		
//		return result;
//	}
//
//	@Override
//	public Driver getDriverByDriverId(Long driverId) {
//		Driver result = (Driver) this.callRow("PKG_DRIVER__P_GET_DRIVER_PROFILE_BY_DRIVER_ID_1",
//			new DriverRowMapper(),
//			driverId);
//		
//		return result;
//	}
//
//	@Override
//	public List<Driver> getCompanyDriverProfileList(int companyId) {
//		List<Driver> result = (List<Driver>) this.callList("PKG_DRIVER__P_GET_DRIVER_PROFILE_LIST_BY_COMPANY_ID_1",
//			new DriverRowMapper(),
//			companyId);
//		
//		return result;
//	}
//
//
//	@Override
//	public List<VehicleDriverAssigment> getVehicleDriverAssignments(int driverId) {
//		List<VehicleDriverAssigment> result = (List<VehicleDriverAssigment>) this.callList("PKG_DRIVER__P_GET_VEHICLE_ASSIGNMENTS_BY_DRIVER_ID_1",
//			new VehicleDriverAssigmentRowMapper(),
//			driverId);
//		
//		return result;
//	}
//
//	@Override
//	public VehicleDriverAssigment saveVehicleDriverAssignment(VehicleDriverAssigment assignment) {
//	    VehicleDriverAssigment result = (VehicleDriverAssigment) this.callRow("PKG_DRIVER__P_SAVE_VEHICLE_ASSIGNMENTS",
//		    new VehicleDriverAssigmentRowMapper(),
//		    assignment.getId(), assignment.getVehicle().getId(), assignment.getDriver().getId(),
//		    assignment.getStartingDate(), assignment.getEndDate());
//	    
//	    return result;
//	}
//
//	@Override
//	public VehicleDriverAssigment saveVehicleDriverAssignmentUnassign(VehicleDriverAssigment assignment) {
//	    VehicleDriverAssigment result = (VehicleDriverAssigment) this.callRow("PKG_DRIVER__P_SAVE_VEHICLE_ASSIGNMENTS_UNASSIGN",
//		    new VehicleDriverAssigmentRowMapper(),
//		    assignment.getId(), assignment.getVehicle().getId(), assignment.getDriver().getId(),
//		    assignment.getStartingDate(), assignment.getEndDate());
//	    
//	    return result;
//	}

}
