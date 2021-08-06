package com.vantalii.data.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.api.data.enums.CompanyType;
import com.project.api.data.model.common.Company;
import com.vantalii.data.service.ICompanyService;

@SuppressWarnings("unchecked")
@Service
public class CompanyService implements ICompanyService {

    @Override
    public List<Company> getCompanyProfilesByCompanyType(CompanyType companyType) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Company getCompanyProfileByCompanyId(int companyId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public int saveCompanyProfile(Company company) {
	// TODO Auto-generated method stub
	return 0;
    }

//    @Override
//    public List<Company> getCompanyProfilesByCompanyType(CompanyType companyType) {
//	List<Company> result = (List<Company>) this.callList("PKG_COMPANY__P_GET_COMPANY_PROFILES_BY_COMPANY_TYPE_1",
//		new CompanyRowMapper(),
//		companyType.getId());
//	return result;
//    }
//
//    @Override
//    public Company getCompanyProfileByCompanyId(int companyId) {
//	Company result = (Company) this.callRow("PKG_COMPANY__P_GET_COMPANY_PROFILE_BY_COMPANY_ID_1",
//		new CompanyRowMapper(),
//		companyId);
//
//	return result;
//    }
//
//    @Override
//    public int saveCompanyProfile(Company company) {
//	int result = this.callInteger("PKG_COMPANY__SAVE_COMPANY_PROFILE",
//		company.getId(), company.getName(), company.getType().getId());
//	
//	return result;
//    }

}
