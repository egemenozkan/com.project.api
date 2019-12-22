package com.project.api.data.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.api.data.enums.Language;
import com.project.api.data.model.gis.City;
import com.project.api.data.model.gis.Country;
import com.project.api.data.model.gis.District;
import com.project.api.data.model.gis.Region;
import com.project.api.data.model.gis.TransferRegion;
import com.project.api.data.model.gis.enums.CityEnum;
import com.project.api.data.model.gis.enums.CountryEnum;
import com.project.api.data.model.gis.enums.DistrictEnum;
import com.project.api.data.model.gis.enums.RegionEnum;
import com.project.api.data.service.IGisService;

@Service
public class GisService implements IGisService {

	@Override
	public List<District> getDistrictsByCity(CityEnum cityEnum, Language language, boolean order) {
		List<District> districts = new ArrayList<>();
		for (DistrictEnum districtEnum : DistrictEnum.values()) {
			if (districtEnum.getCityEnum() == cityEnum) {
				District district = new District();
				district.setId(districtEnum.getId());
				district.setName(districtEnum.getName());
				district.setCity(convertCityEnum(cityEnum));
				district.setOrder(districtEnum.getOrder());
				districts.add(district);
			}
		}
		if (order) {
			districts.sort(Comparator.comparingInt(District::getOrder));
		}
		return districts;
	}

	@Override
	public List<TransferRegion> getTransferRegions() {
		return Collections.emptyList();
	}

	@Override
	public List<Region> getRegionsByDistrict(DistrictEnum districtEnum, Language language, boolean order) {
		List<Region> regions = new ArrayList<>();
		for (RegionEnum regionEnum : RegionEnum.values()) {
			if (regionEnum.getDistrictEnum() == districtEnum) {
				regions.add(convertRegionEnum(regionEnum, language));
			}
		}
		if (order) {
			regions.sort(Comparator.comparingInt(Region::getOrder));
		}
		return regions;

	}

	private Region convertRegionEnum(RegionEnum regionEnum, Language language) {
		Region region = new Region();
		region.setId(regionEnum.getId());
		if (language.getCode().equalsIgnoreCase(Language.TURKISH.getCode())) {
			region.setName(regionEnum.getName());
		} else {
			region.setName(regionEnum.getEnName());
		}
		region.setDistrict(convertDistrictEnum(regionEnum.getDistrictEnum()));
		return region;
	}
	
	
	private District convertDistrictEnum(DistrictEnum districtEnum) {
		District district = new District();
		district.setId(districtEnum.getId());
		district.setCity(convertCityEnum(districtEnum.getCityEnum()));
		return district;
	}
	
	private City convertCityEnum(CityEnum cityEnum) {
		City city = new City();
		city.setId(cityEnum.getId());
		city.setName(cityEnum.getName());
		city.setPlateCode(cityEnum.getPlateCode());
		city.setPhoneCode(cityEnum.getPhoneCode());
		city.setCountry(convertCountryEnum(cityEnum));
		
		return city;
	}

	private Country convertCountryEnum(CityEnum cityEnum) {
		Country country = new Country();
		country.setId(cityEnum.getCountry().getId());
		country.setName(cityEnum.getCountry().getName());
		country.setCode(cityEnum.getCountry().getCode());
		country.setPhoneCode(cityEnum.getCountry().getPhoneCode());
		
		return country;
	}

	@Override
	public List<City> getCitiesByCountry(CountryEnum country, Language language, boolean order) {
		List<City> cities = new ArrayList<>();
		for (CityEnum cityEnum : CityEnum.values()) {
			if (cityEnum.getCountry() == country) {
				cities.add(convertCityEnum(cityEnum));
			}
		}
		return cities;
	}


}
