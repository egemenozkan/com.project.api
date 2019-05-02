package com.project.api.data.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.mapper.PlaceMapper;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.model.place.Place;
import com.project.api.data.service.ISiteMapService;
import com.project.api.utils.WebUtils;

@Service
public class SiteMapService implements ISiteMapService {

	@Autowired
	private PlaceMapper placeMapper;
	
	@Override
	public void updatePlaceSlugs() {
		List<Place> places = placeMapper.findAllPlace("RU");

		if (places != null && !places.isEmpty()) {
			for (Place place : places) {
				List<Localisation> names = placeMapper.findAllPlaceNameByPlaceId(place.getId());
				for (Localisation name : names) {
					placeMapper.savePlaceName(name.getName(), name.getLanguage().getCode(), place.getId(),
							WebUtils.generateSlug(name.getName(), place.getId()));
				}
			}
		}
		
	}

}
