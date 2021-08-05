package com.project.data.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.api.data.enums.ProductType;
import com.project.api.data.mapper.PlaceMapper;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.model.place.Place;
import com.project.common.enums.Language;
import com.project.data.elastic.entity.Suggestion;
import com.project.data.elastic.repository.AutocompleteRepository;
import com.project.data.service.IDataService;

@Service
public class DataService implements IDataService {

	@Autowired
	AutocompleteRepository autocompleteRepository;
	
	@Autowired
	PlaceMapper placeMapper;
	
	@Override
	public List<Suggestion> search(String query, Language language) {
//		return autocompleteRepository.findByLabelContainingIgnoreCaseAndLanguage(query, language);
		return autocompleteRepository.findByLocalisationsNameInIgnoreCase(Arrays.asList(query.split(" ")));
	}

	@Override
	public String save(Suggestion suggestion) {
		return autocompleteRepository.save(suggestion).getId();
	}

	@Override
	public void putPlacesToElasticSearch() {
		List<Place> places = placeMapper.findAllPlace(Language.TURKISH.getCode());
		if (!CollectionUtils.isEmpty(places)) { 
			for (Place place : places) {
				List<Localisation> localisations = placeMapper.findAllPlaceNameByPlaceId(place.getId());
				Suggestion suggestion = new Suggestion();
				suggestion.setPlaceType(place.getType());
				suggestion.setProductType(ProductType.PLACE);
				suggestion.setProductId(place.getId());
				suggestion.setLocalisations(localisations);
				autocompleteRepository.save(suggestion);
			}
			
		}
	}

}
