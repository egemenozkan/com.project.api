package com.vantalii.data.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.api.data.model.common.IdValue;
import com.project.api.data.model.document.TrafficAccident;
import com.project.api.data.model.vehicle.Vehicle;
import com.project.api.data.model.vehicle.VehicleCareAndRepair;
import com.project.api.data.model.vehicle.VehicleDocument;
import com.project.api.data.model.vehicle.VehicleEquipmentType;
import com.vantalii.data.service.IVehicleService;

@Service
public class VehicleService implements IVehicleService {

    @Override
    public Vehicle getVehicleProfileByVehicleId(int vehicleId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int saveVehicle(Vehicle vehicle) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public List<IdValue> getBrands() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<IdValue> getSerialsByBrandId(int brandId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<IdValue> getModelsBySerialId(int serialId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<TrafficAccident> getTrafficAccidentsByVehicleId(int vehicleId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<VehicleEquipmentType> getVehicleEquipmentTypesByVehicleId(int vehicleId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<VehicleCareAndRepair> getVehicleCareAndRepairsByVehicleId(int vehicleId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<VehicleDocument> getVehicleDocumentsByVehicleId(int vehicleId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Vehicle> getVehicleByCompanyId(int companyId) {
	// TODO Auto-generated method stub
	return null;
    }

//    @Override
//    public Vehicle getVehicleProfileByVehicleId(int vehicleId) {
//        Vehicle result = (Vehicle) this.callRow("PKG_VEHICLE__P_GET_VEHICLE_PROFILE_BY_VEHICLE_ID_1",
//        		new VehicleProfileRowMapper(),
//        		vehicleId);
//
//        return result;
//    }
//
//    @Override
//    public int saveVehicle(Vehicle vehicle) {
//        int result = this.callInteger("PKG_VEHICLE__P_SAVE_VEHICLE_PROFILE",
//            	vehicle.getId(), vehicle.getCurrentPlate(), vehicle.getFrontNumber(), vehicle.getType().getId(),
//                    vehicle.getBrand().getId(), vehicle.getSerial().getId(), vehicle.getModel().getId(), vehicle.getColor().getId(),
//                    vehicle.getFirstRegistrationDate(), vehicle.getMaxPassengerLimit(), vehicle.getHasAssistantSeat(), vehicle.getVip() ? 1 : 0);
//        
//        return result;
//    }
//
//    @Override
//    public List<IdValue> getBrands() {
//        List<IdValue> result = (List<IdValue>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_BRANDS",
//        		new IdValueRowMapper());
//        
//        return result;
//    }
//
//    @Override
//    public List<IdValue> getSerialsByBrandId(int brandId) {
//        List<IdValue> result = (List<IdValue>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_SERIALS_BY_BRAND_ID_1",
//        		new IdValueRowMapper(),
//        		brandId);
//        
//        return result;
//    }
//
//    @Override
//    public List<IdValue> getModelsBySerialId(int serialId) {
//        List<IdValue> result = (List<IdValue>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_MODELS_BY_SERIAL_ID_1",
//        		new IdValueRowMapper(),
//        		serialId);
//        
//        return result;
//    }
//
//    @Override
//    public List<TrafficAccident> getTrafficAccidentsByVehicleId(int vehicleId) {
//        List<TrafficAccident> result = (List<TrafficAccident>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_ACCIDENTS_BY_VEHICLE_ID_1",
//        		new TrafficAccidentRowMapper(),
//        		vehicleId);
//        
//        return result;
//    }
//
//    @Override
//    public List<VehicleEquipmentType> getVehicleEquipmentTypesByVehicleId(int vehicleId) {
//        List<IdValue> list = (List<IdValue>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_EQUIPMENTS_BY_VEHICLE_ID_1",
//        		new IdValueRowMapper(),
//        		vehicleId);
//        
//        List<VehicleEquipmentType> types = null;
//        if (list != null) {
//            types = new ArrayList<VehicleEquipmentType>();
//            for (IdValue elem : list) {
//                types.add(VehicleEquipmentType.getById(Integer.valueOf(elem.getValue())));
//            }
//        }
//        
//        return types;
//    }
//
//    @Override
//    public List<VehicleCareAndRepair> getVehicleCareAndRepairsByVehicleId(int vehicleId) {
//        List<VehicleCareAndRepair> result = (List<VehicleCareAndRepair>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_SERVICES_BY_VEHICLE_ID_1",
//        		new VehicleCareAndRepairRowMapper(),
//        		vehicleId);
//        
//        return result;
//    }
//
//    @Override
//    public List<VehicleDocument> getVehicleDocumentsByVehicleId(int vehicleId) {
//        List<VehicleDocument> result = (List<VehicleDocument>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_DOCUMENTS_BY_VEHICLE_ID_1",
//        		new VehicleDocumentRowMapper(),
//        		vehicleId);
//        
//        return result;
//    }
//
//    @Override
//    public List<Vehicle> getVehicleByCompanyId(int companyId) {
//        List<Vehicle> result = (List<Vehicle>) this.callList("PKG_VEHICLE__P_GET_VEHICLE_LIST_BY_COMPANY_ID_1",
//        		new VehicleProfileRowMapper(),
//        		companyId);
//        
//        return result;
//    }
}