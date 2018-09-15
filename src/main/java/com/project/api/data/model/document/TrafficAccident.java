package com.project.api.data.model.document;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public class TrafficAccident {

    private int id;
    private List<TrafficAccidentType> types;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:MM")
    private LocalDate date;
    private String report;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public List<TrafficAccidentType> getTypes() {
	return types;
    }

    public void setTypes(List<TrafficAccidentType> types) {
	this.types = types;
    }

    public LocalDate getDate() {
	return date;
    }

    public void setDate(LocalDate date) {
	this.date = date;
    }

    public String getReport() {
	return report;
    }

    public void setReport(String report) {
	this.report = report;
    }

}
