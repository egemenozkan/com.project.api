package com.project.api.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.model.common.DestinationAutocomplete;
import com.project.api.data.model.common.IdValue;
import com.project.api.data.service.IDatapoolService;
import com.project.common.model.City;
import com.project.common.model.Country;
import com.project.common.model.Nationality;
import com.project.common.model.Region;
import com.project.common.model.Subregion;



@RestController
@RequestMapping(value = "/api/v1/")
public class DatapoolRestController {

    @Autowired
    private IDatapoolService datapoolService;

    @RequestMapping(value = "/nationalities", method = RequestMethod.GET)
    public ResponseEntity<List<Nationality>> getNationalities() {
	
	List<Nationality> nationalities = datapoolService.getNationalities();
	ResponseEntity<List<Nationality>> response = new ResponseEntity<List<Nationality>>(nationalities, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/colors", method = RequestMethod.GET)
    public ResponseEntity<List<IdValue>> getColors() {
	List<IdValue> colors = datapoolService.getColors();
	ResponseEntity<List<IdValue>> response = new ResponseEntity<List<IdValue>>(colors, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ResponseEntity<List<Country>> getCountries() {
	List<Country> countries = datapoolService.getCountries();
	ResponseEntity<List<Country>> response = new ResponseEntity<List<Country>>(countries, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/countries/{countryId}/cities", method = RequestMethod.GET)
    public ResponseEntity<List<City>> getCitiesByCountryId(@PathVariable int countryId) {
	List<City> cities = datapoolService.getCitiesByCountryId(countryId);
	ResponseEntity<List<City>> response = new ResponseEntity<List<City>>(cities, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/cities/{cityId}/regions", method = RequestMethod.GET)
    public ResponseEntity<List<Region>> getRegionsByCityId(@PathVariable int cityId) {
	List<Region> regions = datapoolService.getRegionsByCityId(cityId);
	ResponseEntity<List<Region>> response = new ResponseEntity<List<Region>>(regions, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/regions/{regionId}/subregions", method = RequestMethod.GET)
    public ResponseEntity<List<Subregion>> getCompanies(@PathVariable int regionId) {
	List<Subregion> subregions = datapoolService.getSubregionsByRegionId(regionId);
	ResponseEntity<List<Subregion>> response = new ResponseEntity<List<Subregion>>(subregions, HttpStatus.OK);

	return response;
    }

    @RequestMapping(value = "/destinations", method = RequestMethod.GET)
    public ResponseEntity<List<DestinationAutocomplete>> getDestinations(@RequestParam String query) {
	List<DestinationAutocomplete> destinations = datapoolService.getDestinations();
	if (destinations != null && query != null && !query.isEmpty()) {
	    for (Iterator iterator = destinations.iterator(); iterator.hasNext();) {
		DestinationAutocomplete destinationAutocomplete = (DestinationAutocomplete) iterator.next();
		if (destinationAutocomplete.getName().toLowerCase().indexOf(query) == -1) {
		    iterator.remove();
		}
	    }
	}
	ResponseEntity<List<DestinationAutocomplete>> response = new ResponseEntity<List<DestinationAutocomplete>>(destinations, HttpStatus.OK);

	return response;
    }

}