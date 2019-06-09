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

import com.project.api.data.model.common.IdValue;
import com.project.api.data.model.document.TrafficAccident;
import com.project.api.data.model.vehicle.Vehicle;
import com.project.api.data.model.vehicle.VehicleCareAndRepair;
import com.project.api.data.model.vehicle.VehicleDocument;
import com.project.api.data.service.IVehicleService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/api/v1/")
public class VehicleRestController {
    private final IVehicleService vehicleService;

    @Autowired
    public VehicleRestController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(value = "/companies/{companyId}/vehicles", method= RequestMethod.GET)
    public ResponseEntity<List> getCompanyVehicle(@PathVariable int companyId) {
        List<Vehicle> vehicles = vehicleService.getVehicleByCompanyId(companyId);
        ResponseEntity<List> response = new ResponseEntity<>(vehicles, HttpStatus.OK);
        return response;
    }


    @RequestMapping(value = "/vehicles/{vehicleId}/vehicle-profile", method=RequestMethod.GET)
    public ResponseEntity<Vehicle> getVehicleProfile(@PathVariable int vehicleId) {
        Vehicle vehicle = vehicleService.getVehicleProfileByVehicleId(vehicleId);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/brands", method = RequestMethod.GET)
    public ResponseEntity<List<IdValue>> getVehicleBrands() {
        List<IdValue> vehicleBrands = vehicleService.getBrands();

        return new ResponseEntity<>(vehicleBrands, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/brands/{brandId}/serials", method = RequestMethod.GET)
    public ResponseEntity<List<IdValue>> getVehicleSerialsById(@PathVariable int brandId) {
        List<IdValue> vehicleSerials = vehicleService.getSerialsByBrandId(brandId);
        return new ResponseEntity<>(vehicleSerials, HttpStatus.OK);
    }
    @RequestMapping(value = "/vehicles/brands/serials/{serialId}/models", method = RequestMethod.GET)
    public ResponseEntity<List<IdValue>> getVehicleSerialsByBrandId(@PathVariable int serialId) {
        List<IdValue> vehicleSerials = vehicleService.getModelsBySerialId(serialId);

        return new ResponseEntity<>(vehicleSerials, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/{vehicleId}/documents", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleDocument>> getVehicleDocumentsByVehicleId(@PathVariable int vehicleId) {
        List<VehicleDocument> vehicleDocuments = vehicleService.getVehicleDocumentsByVehicleId(vehicleId);

        return new ResponseEntity<>(vehicleDocuments, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/{vehicleId}/accidents", method = RequestMethod.GET)
    public ResponseEntity<List<TrafficAccident>> getTrafficAccidentsByVehicleId(@PathVariable int vehicleId) {
        List<TrafficAccident> trafficAccidents = vehicleService.getTrafficAccidentsByVehicleId(vehicleId);

        return new ResponseEntity<>(trafficAccidents, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicles/{vehicleId}/services", method = RequestMethod.GET)
    public ResponseEntity<List<VehicleCareAndRepair>> getVehicleVehicleCareAndRepairs(@PathVariable int vehicleId) {
        List<VehicleCareAndRepair> services = vehicleService.getVehicleCareAndRepairsByVehicleId(vehicleId);

        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @RequestMapping(value = "/vehicle/vehicle-profile/save", method=RequestMethod.POST)
    public ResponseEntity<Integer> saveVehicle(RequestEntity<Vehicle> requestEntity) {
        Vehicle vehicle = requestEntity.getBody();
        int vehicleId =  vehicleService.saveVehicle(vehicle);
        ResponseEntity<Integer> response = new ResponseEntity<Integer>(vehicleId, HttpStatus.OK);

        return response;
    }
}
