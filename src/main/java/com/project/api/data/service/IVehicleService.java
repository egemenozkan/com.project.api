package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.common.IdValue;
import com.project.api.data.model.document.TrafficAccident;
import com.project.api.data.model.vehicle.Vehicle;
import com.project.api.data.model.vehicle.VehicleCareAndRepair;
import com.project.api.data.model.vehicle.VehicleDocument;
import com.project.api.data.model.vehicle.VehicleEquipmentType;

public interface IVehicleService {
    Vehicle getVehicleProfileByVehicleId(int vehicleId);

    int saveVehicle(Vehicle vehicle);

    List<IdValue> getBrands();

    List<IdValue> getSerialsByBrandId(int brandId);

    List<IdValue> getModelsBySerialId(int serialId);

    List<TrafficAccident> getTrafficAccidentsByVehicleId(int vehicleId);

    List<VehicleEquipmentType> getVehicleEquipmentTypesByVehicleId(int vehicleId);

    List<VehicleCareAndRepair> getVehicleCareAndRepairsByVehicleId(int vehicleId);

    List<VehicleDocument> getVehicleDocumentsByVehicleId(int vehicleId);

    List<Vehicle> getVehicleByCompanyId(int companyId);
}
