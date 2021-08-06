package com.vantalii.api.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.project.api.data.enums.ProductType;
import com.project.api.data.model.autocomplete.AutocompleteRequest;
import com.project.api.data.model.autocomplete.AutocompleteResponse;
import com.project.api.data.model.autocomplete.Item;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.model.place.Place;
import com.project.common.enums.Language;
import com.vantalii.api.data.mapper.PlaceMapper;
import com.vantalii.api.service.IAutocompleteService;
import com.vantalii.data.elastic.entity.Suggestion;
import com.vantalii.data.elastic.repository.AutocompleteRepository;
import com.vantalii.data.service.IDataService;

@Service("autocopleteServiceNew")
public class AutocompleteService implements IAutocompleteService {

	@Autowired
	IDataService dataService;

	@Autowired
	Gson gson;
	
	@Autowired
	PlaceMapper placeMapper;
	

	@Autowired
	AutocompleteRepository autocompleteRepository;

	private static final Logger logger = LogManager.getLogger(AutocompleteService.class);

	@Override
	public AutocompleteResponse autocomplete(AutocompleteRequest autocompleteRequest) {
		AutocompleteResponse autocompleteResponse = new AutocompleteResponse();
		if (autocompleteRequest == null || autocompleteRequest.getQuery().isBlank()) {
			autocompleteResponse.setSuccess(false);
			return autocompleteResponse;
		}

		List<Suggestion> suggestions = dataService.search(autocompleteRequest.getQuery(),
				autocompleteRequest.getLanguage());

		if (CollectionUtils.isEmpty(suggestions)) {
			autocompleteResponse.setItems(Collections.emptyList());
			return autocompleteResponse;
		}
		List<Item> items = new ArrayList<>();
		String languageCode = autocompleteRequest.getLanguage().getCode();

		for (int i = 0; i < suggestions.size(); i++) {
			Optional<Localisation> localisation = suggestions.get(i).getLocalisations().stream()
					.filter(l -> languageCode.equalsIgnoreCase(l.getLanguage().getCode())).findFirst();
			if (localisation.isPresent()) {
				Item item = new Item();
				item.setLabel(localisation.get().getName());
				item.setId(suggestions.get(i).getProductId());
				item.setProductType(suggestions.get(i).getProductType());
				if (item.getProductType() == ProductType.EVENT) {
					item.setType(suggestions.get(i).getEventType().toString());
				} else {
					item.setType(suggestions.get(i).getPlaceType().toString());
				}
				item.setLanguage(Language.getByCode(languageCode));
				StringBuilder urlBuilder = new StringBuilder();
				if (Language.RUSSIAN != Language.getByCode(languageCode)) {
					urlBuilder.append("/").append(languageCode.toLowerCase());
				}
				urlBuilder.append("/places/").append(localisation.get().getSlug());
				item.setUrl(urlBuilder.toString());
				items.add(item);
			}
		}
//		items = items == null ? new ArrayList<Item>() : items;
		if (CollectionUtils.isEmpty(items)) {
			autocompleteResponse.setSuccess(Boolean.FALSE);
			return autocompleteResponse;
		}

		autocompleteResponse.setSuccess(Boolean.TRUE);
		autocompleteResponse.setItems(items);

		return autocompleteResponse;
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
