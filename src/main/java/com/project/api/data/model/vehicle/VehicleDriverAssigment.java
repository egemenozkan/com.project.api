package com.project.api.data.model.vehicle;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.project.api.data.model.driver.Driver;

public class VehicleDriverAssigment {
    private int id;
    private Vehicle vehicle;
    private Driver driver;
    private int assignerId;
    private String assigner;
    private int unassignerId;
    private String unassigner;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime assignedDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime unassignedDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startingDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime endDate;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public int getAssignerId() {
        return assignerId;
    }
    public void setAssignerId(int assignerId) {
        this.assignerId = assignerId;
    }
    public String getAssigner() {
        return assigner;
    }
    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }
    public int getUnassignerId() {
        return unassignerId;
    }
    public void setUnassignerId(int unassignerId) {
        this.unassignerId = unassignerId;
    }
    public String getUnassigner() {
        return unassigner;
    }
    public void setUnassigner(String unassigner) {
        this.unassigner = unassigner;
    }
    public LocalDateTime getAssignedDate() {
        return assignedDate;
    }
    public void setAssignedDate(LocalDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }
    public LocalDateTime getUnassignedDate() {
        return unassignedDate;
    }
    public void setUnassignedDate(LocalDateTime unassignedDate) {
        this.unassignedDate = unassignedDate;
    }
    public LocalDateTime getStartingDate() {
        return startingDate;
    }
    public void setStartingDate(LocalDateTime startingDate) {
        this.startingDate = startingDate;
    }
    public LocalDateTime getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
   

}
