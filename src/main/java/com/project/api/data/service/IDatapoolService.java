package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.common.DestinationAutocomplete;
import com.project.api.data.model.common.IdValue;
import com.project.api.data.model.gis.City;
import com.project.api.data.model.gis.Country;
import com.project.api.data.model.gis.Region;
import com.project.api.data.model.gis.Subregion;
import com.project.common.model.Nationality;

public interface IDatapoolService {
	List<Nationality> getNationalities();
	List<Country> getCountries();
	List<IdValue> getColors();
	List<City>	getCitiesByCountryId(int countryId);
	List<Region>	getRegionsByCityId(int cityId);
	List<Subregion> getSubregionsByRegionId(int regionId);
	List<DestinationAutocomplete> getDestinations();
}
