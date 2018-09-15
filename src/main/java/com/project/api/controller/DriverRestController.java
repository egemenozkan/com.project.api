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

import com.project.api.data.model.common.Email;
import com.project.api.data.model.common.Phone;
import com.project.api.data.model.driver.Driver;
import com.project.api.data.model.vehicle.VehicleDriverAssigment;
import com.project.api.data.service.IDriverService;
import com.project.api.data.service.IUserService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/api/v1/")
public class DriverRestController {
	@Autowired
	private IDriverService driverService;
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value = "/drivers/driver-profile/save", method=RequestMethod.POST)
	public ResponseEntity<Long> saveUser(RequestEntity<Driver> requestEntity) {
		Driver driver = requestEntity.getBody();
		long driverId =  driverService.saveDriverProfile(driver);
		ResponseEntity<Long> response = new ResponseEntity<Long>(driverId, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/driver-profile", method=RequestMethod.GET)
	public ResponseEntity<Driver> getDriverProfile(@PathVariable Long driverId) {
		Driver driver = driverService.getDriverProfileByDriverId(driverId);
		ResponseEntity<Driver> response = new ResponseEntity<Driver>(driver, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}", method=RequestMethod.GET)
	public ResponseEntity<Driver> getDriver(@PathVariable Long driverId) {
		Driver driver = driverService.getDriverByDriverId(driverId);
		ResponseEntity<Driver> response = new ResponseEntity<Driver>(driver, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/phones", method=RequestMethod.GET)
	public ResponseEntity<List> getDriverPhones(@PathVariable int driverId) {
		List<Phone> phones = userService.getPhonesByUserId(driverId);
		ResponseEntity<List> response = new ResponseEntity<List>(phones, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/phones/save", method=RequestMethod.POST)
	public ResponseEntity<Phone> saveDriverPhone(@PathVariable int driverId, RequestEntity<Phone> requestEntity) {
	    	Phone phone = requestEntity.getBody();
		Phone updatedPhone = userService.savePhone(driverId, phone);
		ResponseEntity<Phone> response = new ResponseEntity<Phone>(updatedPhone, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/emails", method=RequestMethod.GET)
	public ResponseEntity<List> getDriverEmails(@PathVariable int driverId) {
		List<Email> emails = userService.getEmailsByUserId(driverId);
		ResponseEntity<List> response = new ResponseEntity<List>(emails, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/emails/save", method=RequestMethod.POST)
	public ResponseEntity<Email> saveDriverEmail(@PathVariable int driverId, RequestEntity<Email> requestEntity) {
	    	Email email = requestEntity.getBody();
	    	Email updatedEmail = userService.saveEmail(driverId, email);
		ResponseEntity<Email> response = new ResponseEntity<Email>(updatedEmail, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/addresses", method=RequestMethod.GET)
	public ResponseEntity<List> getDriverAddresses(@PathVariable int driverId) {
		List<Phone> phones = userService.getPhonesByUserId(driverId);
		ResponseEntity<List> response = new ResponseEntity<List>(phones, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/addresses/save", method=RequestMethod.POST)
	public ResponseEntity<Phone> saveDriverAddresses(@PathVariable int driverId, RequestEntity<Phone> requestEntity) {
	    	Phone phone = requestEntity.getBody();
		Phone updatedPhone = userService.savePhone(driverId, phone);
		ResponseEntity<Phone> response = new ResponseEntity<Phone>(updatedPhone, HttpStatus.OK);
		
		return response;
	}
	
        @RequestMapping(value = "/drivers/{driverId}/vehicles/assignments", method = RequestMethod.GET)
        public ResponseEntity<List<VehicleDriverAssigment>> getVehicleDriverAssignments(@PathVariable int driverId) {
    	List<VehicleDriverAssigment> assignments = driverService.getVehicleDriverAssignments(driverId);
    
    	return new ResponseEntity<>(assignments, HttpStatus.OK);
        }
        
	@RequestMapping(value = "/drivers/{driverId}/vehicles/assignments/save", method=RequestMethod.POST)
	public ResponseEntity<VehicleDriverAssigment> saveVehicleAssignment(@PathVariable int driverId, RequestEntity<VehicleDriverAssigment> requestEntity) {
	    	VehicleDriverAssigment assignment = requestEntity.getBody();
	    	VehicleDriverAssigment updatedAssignment = driverService.saveVehicleDriverAssignment(assignment);
		ResponseEntity<VehicleDriverAssigment> response = new ResponseEntity<VehicleDriverAssigment>(updatedAssignment, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/drivers/{driverId}/vehicles/assignments/save/unassign", method=RequestMethod.POST)
	public ResponseEntity<VehicleDriverAssigment> saveVehicleAssignmentUnassign(@PathVariable int driverId, RequestEntity<VehicleDriverAssigment> requestEntity) {
	    	VehicleDriverAssigment assignment = requestEntity.getBody();
	    	VehicleDriverAssigment updatedAssignment = driverService.saveVehicleDriverAssignmentUnassign(assignment);
		ResponseEntity<VehicleDriverAssigment> response = new ResponseEntity<VehicleDriverAssigment>(updatedAssignment, HttpStatus.OK);
		
		return response;
	}
}

