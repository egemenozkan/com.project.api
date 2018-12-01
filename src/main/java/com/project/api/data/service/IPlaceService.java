package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.place.Place;
import com.project.common.model.AutocompleteResponse;

public interface IPlaceService {
    AutocompleteResponse autocompletePlace(String query); 
    Place findPlaceById(long id);
    List<Place> findAllPlace(String language, String originalLanguage);
    Place savePlace(Place place);
}
