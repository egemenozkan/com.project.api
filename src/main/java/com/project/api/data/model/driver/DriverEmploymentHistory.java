package com.project.api.data.model.driver;

import java.time.LocalDate;
import java.util.List;

import com.project.api.data.model.common.Company;
import com.project.api.data.model.document.TrafficAccident;

public class DriverEmploymentHistory {

    private LocalDate startDate;
    private LocalDate endDate;
    private ReasonOfLeavingType reasonOfLeavingType;
    private String note;
    private List<TrafficAccident> trafficAccidents;
    private Company employer;
    
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public ReasonOfLeavingType getReasonOfLeavingType() {
		return reasonOfLeavingType;
	}
	public void setReasonOfLeavingType(ReasonOfLeavingType reasonOfLeavingType) {
		this.reasonOfLeavingType = reasonOfLeavingType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<TrafficAccident> getTrafficAccidents() {
		return trafficAccidents;
	}
	public void setTrafficAccidents(List<TrafficAccident> trafficAccidents) {
		this.trafficAccidents = trafficAccidents;
	}
	public Company getEmployer() {
		return employer;
	}
	public void setEmployer(Company employer) {
		this.employer = employer;
	}
    
    

}
