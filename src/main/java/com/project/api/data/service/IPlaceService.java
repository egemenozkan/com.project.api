package com.project.api.data.service;

import java.util.List;

import com.project.api.data.enums.Language;
import com.project.api.data.enums.MainType;
import com.project.api.data.enums.PlaceType;
import com.project.api.data.model.event.TimeTable;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.PlaceLandingPage;
import com.project.api.data.model.place.PlaceRequest;

public interface IPlaceService {
//    AutocompleteResponse autocompletePlace(String query); 
    List<Place> autocomplete(String name, Language language);
	Place findPlaceById(long id,String language);
    List<Place> findAllPlace(String language);
    List<Place> findAllPlaceByType(String language, PlaceType type);
    List<Place> findAllPlaceByMainType(String language, MainType mainType);
    Place savePlace(Place place, int star);
    PlaceLandingPage findLandingPageByPlaceIdAndLanguage(long id, String language);
    void saveLandingPage(PlaceLandingPage page);
    List<PlaceLandingPage> findAllLandingPageByFilter(PlaceRequest placeRequest);
    boolean setMainImage(long id, long fileId);
	int deleteTimeTableById(long id);
	int saveTimeTable(TimeTable body);
	List<TimeTable> getTimeTableByPlaceId(long id);
	List<Place> findAllPlaceByFilter(PlaceRequest placeRequest);
}
