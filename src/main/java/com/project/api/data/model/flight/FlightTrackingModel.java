package com.project.api.data.model.flight;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.project.api.data.enums.FlightTrackingType;

public class FlightTrackingModel {
    private int id;
    private int airlineId;
    private String airlineCode;
    private String airline;
    private String flightNo;
    private int deptAirportId;
    private String deptAirportCode;
    private String deptAirport;
    private int arrAirportId;
    private String arrAirportCode;
    private String arrAirport;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime scheduledTime;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime estimatedTime;
    private String belt;
    private String remark;
    private String terminal;
    private FlightTrackingType type; // International Domestic Arrival Departure IA ID DA DD
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate flightDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;
    private int transitAirportId;
    private String transitAirport;
    private String gate;
    private String airlineLogo;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getAirlineId() {
	return airlineId;
    }

    public void setAirlineId(int airlineId) {
	this.airlineId = airlineId;
    }

    public String getAirlineCode() {
	return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
	this.airlineCode = airlineCode;
    }

    public String getAirline() {
	return airline;
    }

    public void setAirline(String airline) {
	this.airline = airline;
    }

    public String getFlightNo() {
	return flightNo;
    }

    public void setFlightNo(String flightNo) {
	this.flightNo = flightNo;
    }

    public int getDeptAirportId() {
	return deptAirportId;
    }

    public void setDeptAirportId(int deptAirportId) {
	this.deptAirportId = deptAirportId;
    }

    public String getDeptAirportCode() {
	return deptAirportCode;
    }

    public void setDeptAirportCode(String deptAirportCode) {
	this.deptAirportCode = deptAirportCode;
    }

    public String getDeptAirport() {
	return deptAirport;
    }

    public void setDeptAirport(String deptAirport) {
	this.deptAirport = deptAirport;
    }

    public int getArrAirportId() {
	return arrAirportId;
    }

    public void setArrAirportId(int arrAirportId) {
	this.arrAirportId = arrAirportId;
    }

    public String getArrAirportCode() {
	return arrAirportCode;
    }

    public void setArrAirportCode(String arrAirportCode) {
	this.arrAirportCode = arrAirportCode;
    }

    public String getArrAirport() {
	return arrAirport;
    }

    public void setArrAirport(String arrAirport) {
	this.arrAirport = arrAirport;
    }

    public LocalDateTime getScheduledTime() {
	return scheduledTime;
    }

    public void setScheduledTime(LocalDateTime scheduledTime) {
	this.scheduledTime = scheduledTime;
    }

    public LocalDateTime getEstimatedTime() {
	return estimatedTime;
    }

    public void setEstimatedTime(LocalDateTime estimatedTime) {
	this.estimatedTime = estimatedTime;
    }

    public String getBelt() {
	return belt;
    }

    public void setBelt(String belt) {
	this.belt = belt;
    }

    public String getRemark() {
	return remark;
    }

    public void setRemark(String remark) {
	this.remark = remark;
    }

    public String getTerminal() {
	return terminal;
    }

    public void setTerminal(String terminal) {
	this.terminal = terminal;
    }

    public FlightTrackingType getType() {
	return type;
    }

    public void setType(FlightTrackingType type) {
	this.type = type;
    }

    public LocalDate getFlightDate() {
	return flightDate;
    }

    public void setFlightDate(LocalDate flightDate) {
	this.flightDate = flightDate;
    }

    public LocalDateTime getUpdatedAt() {
	return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
	this.updatedAt = updatedAt;
    }

    public int getTransitAirportId() {
	return transitAirportId;
    }

    public void setTransitAirportId(int transitAirportId) {
	this.transitAirportId = transitAirportId;
    }

    public String getTransitAirport() {
	return transitAirport;
    }

    public void setTransitAirport(String transitAirport) {
	this.transitAirport = transitAirport;
    }

    public String getGate() {
	return gate;
    }

    public void setGate(String gate) {
	this.gate = gate;
    }

    public String getAirlineLogo() {
	return airlineLogo;
    }

    public void setAirlineLogo(String airlineLogo) {
	this.airlineLogo = airlineLogo;
    }

}
