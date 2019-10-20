package com.project.api.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.mapper.DatapoolMapper;
import com.project.api.data.model.common.DestinationAutocomplete;
import com.project.api.data.model.common.IdValue;
import com.project.api.data.model.gis.City;
import com.project.api.data.model.gis.Country;
import com.project.api.data.model.gis.Region;
import com.project.api.data.model.gis.Subregion;
import com.project.api.data.service.IDatapoolService;
import com.project.common.BaseService;
import com.project.common.model.Nationality;

@SuppressWarnings("unchecked")
@Service
public class DatapoolService extends BaseService implements IDatapoolService {

	@Autowired
	private DatapoolMapper datapoolMapper;

	@Override
	public List<Nationality> getNationalities() {
		return datapoolMapper.getNationalities();
	}

	@Override
	public List<Country> getCountries() {
		return null; //datapoolMapper.getCountries();
	}

	@Override
	public List<IdValue> getColors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		return null; //datapoolMapper.getCitiesByCountryId(countryId);
	}

	@Override
	public List<Region> getRegionsByCityId(int cityId) {
		return null; // datapoolMapper.getRegionsByCityId(cityId);
	}

	@Override
	public List<Subregion> getSubregionsByRegionId(int regionId) {
		return null; // datapoolMapper.getSubregionsByRegionId(regionId);
	}

	@Override
	public List<DestinationAutocomplete> getDestinations() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public List<IdValue> getNationalities() {
	// List<IdValue> result = (List<IdValue>)
	// this.callList("PKG_DATAPOOL__P_GET_NATIONALITIES", new IdValueRowMapper());
	//
	// return result;
	// }
	//
	// @Override
	// public List<Country> getCountries() {
	// List<Country> result = (List<Country>)
	// this.callList("PKG_DATAPOOL__P_GET_COUNTRIES", new CountryRowMapper());
	//
	// return result;
	// }
	//
	// @Override
	// public List<IdValue> getColors() {
	// List<IdValue> result = (List<IdValue>)
	// this.callList("PKG_DATAPOOL__P_GET_COLORS", new IdValueRowMapper());
	//
	// return result;
	// }
	//
	// @Override
	// public List<City> getCitiesByCountryId(int countryId) {
	// List<City> result = (List<City>)
	// this.callList("PKG_DATAPOOL__P_GET_CITIES_BY_COUNTRY_ID", new
	// CityRowMapper(),
	// countryId);
	//
	// return result;
	// }
	//
	// @Override
	// public List<Region> getRegionsByCityId(int cityId) {
	// List<Region> result = (List<Region>)
	// this.callList("PKG_DATAPOOL__P_GET_REGIONS_BY_CITY_ID", new
	// RegionRowMapper(),
	// cityId);
	//
	// return result;
	// }
	//
	// @Override
	// public List<Subregion> getSubregionsByRegionId(int regionId) {
	// List<Subregion> result = (List<Subregion>)
	// this.callList("PKG_DATAPOOL__P_GET_SUBREGIONS_BY_REGION_ID", new
	// SubregionRowMapper(),
	// regionId);
	//
	// return result;
	// }
	//
	// @Override
	// public List<DestinationAutocomplete> getDestinations() {
	// List<DestinationAutocomplete> result = (List<DestinationAutocomplete>)
	// this.callList("PKG_DATAPOOL__P_GET_DESTINATIONS", new
	// DestinationAutocompleteRowMapper());
	//
	// return result;
	// }

}
