package com.project.api.data.model.driver;

import java.util.List;

import com.project.api.data.model.common.Company;
import com.project.api.data.model.common.Person;

public class Driver extends Person {
    private String licenceNo;
    private List<DrivingLicence> drivingLicences;
    private List<DriverDocument> driverDocuments;
    private List<DriverProsthesis> prosthesises;
    private List<DriverEmploymentHistory> employmentHistories;
    private Company currentCompany;

    public Company getCurrentCompany() {
	return currentCompany;
    }

    public void setCurrentCompany(Company currentCompany) {
	this.currentCompany = currentCompany;
    }

    public String getLicenceNo() {
	return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
	this.licenceNo = licenceNo;
    }

    public List<DrivingLicence> getDrivingLicences() {
	return drivingLicences;
    }

    public void setDrivingLicences(List<DrivingLicence> drivingLicences) {
	this.drivingLicences = drivingLicences;
    }

    public List<DriverDocument> getDriverDocuments() {
	return driverDocuments;
    }

    public void setDriverDocuments(List<DriverDocument> driverDocuments) {
	this.driverDocuments = driverDocuments;
    }

    public List<DriverEmploymentHistory> getEmploymentHistories() {
	return employmentHistories;
    }

    public void setEmploymentHistories(List<DriverEmploymentHistory> employmentHistories) {
	this.employmentHistories = employmentHistories;
    }

    public List<DriverProsthesis> getProsthesises() {
	return prosthesises;
    }

    public void setProsthesises(List<DriverProsthesis> prosthesises) {
	this.prosthesises = prosthesises;
    }
}
