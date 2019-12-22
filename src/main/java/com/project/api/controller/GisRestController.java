package com.project.api.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.enums.Language;
import com.project.api.data.model.gis.City;
import com.project.api.data.model.gis.District;
import com.project.api.data.model.gis.Region;
import com.project.api.data.model.gis.enums.CityEnum;
import com.project.api.data.model.gis.enums.CountryEnum;
import com.project.api.data.model.gis.enums.DistrictEnum;
import com.project.api.data.service.IGisService;

@RestController
@RequestMapping(value = "/api/v1/")
public class GisRestController {
	
	@Autowired
	IGisService gisService;
	
	@GetMapping("/gis/countries/{id}/cities")
	public @ResponseBody List<City> getCitiesByCountry(@PathVariable int id, @RequestParam(defaultValue = "ru") String language, @RequestParam(required = false) boolean order) {
		if (CityEnum.getById(id) != null) {
			return gisService.getCitiesByCountry(CountryEnum.getById(id), Language.getByCode(language), order);
		}
		return Collections.emptyList();
	}
	
	@GetMapping("/gis/cities/{id}/districts")
	public @ResponseBody List<District> getDistrictsByCity(@PathVariable int id, @RequestParam(defaultValue = "ru") String language, @RequestParam(required = false) boolean order) {
		if (CityEnum.getById(id) != null) {
			return gisService.getDistrictsByCity(CityEnum.getById(id), Language.getByCode(language), order);
		}
		return Collections.emptyList();
	}
	
	@GetMapping("/gis/districts/{id}/regions")
	public @ResponseBody List<Region> getRegionsByDistrict(@PathVariable int id, @RequestParam(defaultValue = "ru") String language, @RequestParam(required = false) boolean order) {
		if (DistrictEnum.getById(id) != null) {
			return gisService.getRegionsByDistrict(DistrictEnum.getById(id), Language.getByCode(language), order);
		}
		return Collections.emptyList();
	}
	
}
