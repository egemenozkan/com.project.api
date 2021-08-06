package com.vantalii.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.enums.CompanyType;
import com.project.api.data.model.common.Company;
import com.project.api.data.model.driver.Driver;
import com.vantalii.data.service.ICompanyService;
import com.vantalii.data.service.IDriverService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping(value = "/api/v1/")
public class CompanyRestController<T> {
	@Autowired
	private IDriverService driverService;
	@Autowired
	private ICompanyService companyService;
	
	@RequestMapping(value = "/companies/{companyId}/drivers", method=RequestMethod.GET)
	public ResponseEntity<List> getCompanyDrivers(RequestEntity<T> requestEntity, @PathVariable int companyId) {
		List<Driver> drivers = driverService.getCompanyDriverProfileList(companyId);
		ResponseEntity<List> response = new ResponseEntity<List>(drivers, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/companies/company-profile/save", method=RequestMethod.POST)
	public ResponseEntity<Integer> saveCompany(RequestEntity<Company> requestEntity) {
		Company company = requestEntity.getBody();
		int companyId =  companyService.saveCompanyProfile(company);
		ResponseEntity<Integer> response = new ResponseEntity<Integer>(companyId, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/companies/{companyId}/company-profile", method=RequestMethod.GET)
	public ResponseEntity<Company> getDriverProfile(@PathVariable int companyId) {
	    	Company company = companyService.getCompanyProfileByCompanyId(companyId);
		ResponseEntity<Company> response = new ResponseEntity<Company>(company, HttpStatus.OK);
		
		return response;
	}
	
	@RequestMapping(value = "/companies/{companyType}", method=RequestMethod.GET)
	public ResponseEntity<List> getCompanies(@PathVariable int companyType) {
		List<Company> companies = companyService.getCompanyProfilesByCompanyType(CompanyType.getById(companyType));
		ResponseEntity<List> response = new ResponseEntity<List>(companies, HttpStatus.OK);
		
		return response;
	}
	
	
}
