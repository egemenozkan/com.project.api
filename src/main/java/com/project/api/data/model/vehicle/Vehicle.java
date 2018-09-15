package com.project.api.data.model.vehicle;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.project.api.data.model.common.Company;
import com.project.api.data.model.common.IdValue;
import com.project.api.data.model.document.TrafficAccident;

public class Vehicle {
    private Long id;
    private String currentPlate;
    private String frontNumber; // Or glass number
    private IdValue brand;
    private IdValue serial;
    private IdValue model;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate firstRegistrationDate;    //trafiğe çıkış tarihi
    private IdValue color;
    private int maxPassengerLimit;
    private Boolean hasAssistantSeat;
    private VehicleType type;
    private List<VehicleDocument> documents;
    private List<TrafficAccident> trafficAccidents;
    private List<VehicleEquipmentType> equipmentTypes;
    private List<VehicleCareAndRepair> vehicleCareAndRepairs;
    private Company ownerCompany;
    private Boolean vip;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCurrentPlate() {
        return currentPlate;
    }


    public void setCurrentPlate(String currentPlate) {
        this.currentPlate = currentPlate;
    }


    public String getFrontNumber() {
        return frontNumber;
    }


    public void setFrontNumber(String frontNumber) {
        this.frontNumber = frontNumber;
    }


    public IdValue getBrand() {
        return brand;
    }


    public void setBrand(IdValue brand) {
        this.brand = brand;
    }


    public IdValue getSerial() {
        return serial;
    }


    public void setSerial(IdValue serial) {
        this.serial = serial;
    }


    public IdValue getModel() {
        return model;
    }


    public void setModel(IdValue model) {
        this.model = model;
    }


    public LocalDate getFirstRegistrationDate() {
        return firstRegistrationDate;
    }


    public void setFirstRegistrationDate(LocalDate firstRegistrationDate) {
        this.firstRegistrationDate = firstRegistrationDate;
    }


    public IdValue getColor() {
        return color;
    }


    public void setColor(IdValue color) {
        this.color = color;
    }


    public int getMaxPassengerLimit() {
        return maxPassengerLimit;
    }


    public void setMaxPassengerLimit(int maxPassengerLimit) {
        this.maxPassengerLimit = maxPassengerLimit;
    }


    public Boolean getHasAssistantSeat() {
        return hasAssistantSeat;
    }


    public void setHasAssistantSeat(Boolean hasAssistantSeat) {
        this.hasAssistantSeat = hasAssistantSeat;
    }


    public VehicleType getType() {
        return type;
    }


    public void setType(VehicleType type) {
        this.type = type;
    }


    public List<VehicleDocument> getDocuments() {
        return documents;
    }


    public void setDocuments(List<VehicleDocument> documents) {
        this.documents = documents;
    }


    public List<TrafficAccident> getTrafficAccidents() {
        return trafficAccidents;
    }


    public void setTrafficAccidents(List<TrafficAccident> trafficAccidents) {
        this.trafficAccidents = trafficAccidents;
    }


    public List<VehicleEquipmentType> getEquipmentTypes() {
        return equipmentTypes;
    }


    public void setEquipmentTypes(List<VehicleEquipmentType> equipmentTypes) {
        this.equipmentTypes = equipmentTypes;
    }


    public List<VehicleCareAndRepair> getVehicleCareAndRepairs() {
        return vehicleCareAndRepairs;
    }


    public void setVehicleCareAndRepairs(List<VehicleCareAndRepair> vehicleCareAndRepairs) {
        this.vehicleCareAndRepairs = vehicleCareAndRepairs;
    }


    public Company getOwnerCompany() {
        return ownerCompany;
    }


    public void setOwnerCompany(Company ownerCompany) {
        this.ownerCompany = ownerCompany;
    }


    public Boolean getVip() {
	return vip;
    }


    public void setVip(Boolean vip) {
	this.vip = vip;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Vehicle) {
            Vehicle toCompare = (Vehicle) o;
            return this.id.equals(toCompare.id);
        }
        return false;
    }
}
