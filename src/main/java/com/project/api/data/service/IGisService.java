package com.project.api.data.service;

import java.util.List;

import com.project.common.enums.Language;
import com.project.api.data.model.gis.City;
import com.project.api.data.model.gis.District;
import com.project.api.data.model.gis.Region;
import com.project.api.data.model.gis.TransferRegion;
import com.project.api.data.model.gis.enums.CityEnum;
import com.project.api.data.model.gis.enums.CountryEnum;
import com.project.api.data.model.gis.enums.DistrictEnum;

public interface IGisService {

	/* İlçe */
	List<District> getDistrictsByCity(CityEnum city, Language language, boolean order);
	/* Bölge */
	List<Region> getRegionsByDistrict(DistrictEnum district, Language language, boolean order);
	/* Şehir */
	List<City> getCitiesByCountry(CountryEnum country, Language language, boolean order);
	
	List<TransferRegion> getTransferRegions();
}
