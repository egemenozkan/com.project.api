package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.common.DestinationAutocomplete;
import com.project.api.data.model.common.IdValue;
import com.project.common.model.City;
import com.project.common.model.Country;
import com.project.common.model.Nationality;
import com.project.common.model.Region;
import com.project.common.model.Subregion;

public interface IDatapoolService {
	List<Nationality> getNationalities();
	List<Country> getCountries();
	List<IdValue> getColors();
	List<City>	getCitiesByCountryId(int countryId);
	List<Region>	getRegionsByCityId(int cityId);
	List<Subregion> getSubregionsByRegionId(int regionId);
	List<DestinationAutocomplete> getDestinations();
}
