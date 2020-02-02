package com.project.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.api.data.enums.MainType;
import com.project.api.data.enums.ProductType;
import com.project.api.data.model.autocomplete.AutocompleteRequest;
import com.project.api.data.model.autocomplete.AutocompleteResponse;
import com.project.api.data.model.autocomplete.Item;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.PlaceRequest;
import com.project.api.data.service.IEventService;
import com.project.api.data.service.IPlaceService;
import com.project.api.service.IAutocompleteService;

@Service
public class AutocompleteService implements IAutocompleteService {

	@Autowired
	private IPlaceService placeService;
	@Autowired
	private IEventService eventService;

	@Override
	public AutocompleteResponse autocomplete(AutocompleteRequest autocompleteRequest) {
		AutocompleteResponse autocompleteResponse = new AutocompleteResponse();
		if (autocompleteRequest == null || autocompleteRequest.getQuery().isBlank()) {
			autocompleteResponse.setSuccess(false);
			return autocompleteResponse;
		}

		List<Item> items = null;

		PlaceRequest placeRequest = new PlaceRequest();
		placeRequest.setLanguage(autocompleteRequest.getLanguage());
//		placeRequest.setMainType(MainType.FOOD_AND_BEVERAGE);
		placeRequest.setName(autocompleteRequest.getQuery());
		List<Place> places = placeService.findAllPlaceByFilter(placeRequest);

		if (!CollectionUtils.isEmpty(places)) {
			items = new ArrayList<>();
			for (Place place : places) {
				items.add(new Item(place.getId(), place.getName(), place.getUrl(), ProductType.PLACE));
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

}
