package com.vantalii.data.service;

import java.util.List;

import com.project.api.data.enums.CompanyType;
import com.project.api.data.model.common.Company;

public interface ICompanyService {
	List<Company> getCompanyProfilesByCompanyType(CompanyType companyType);
	Company getCompanyProfileByCompanyId(int companyId);
	int saveCompanyProfile(Company company);

}
