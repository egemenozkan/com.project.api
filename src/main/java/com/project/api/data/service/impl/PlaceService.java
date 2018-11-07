package com.project.api.data.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.project.api.data.enums.PlaceType;
import com.project.api.data.mapper.HotelMapper;
import com.project.api.data.mapper.PlaceMapper;
import com.project.api.data.model.common.Address;
import com.project.api.data.model.common.MyPlace;
import com.project.api.data.model.flight.Airport;
import com.project.api.data.model.hotel.Hotel;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.PlaceAutocompleteData;
import com.project.api.data.service.IPlaceService;
import com.project.common.model.AutocompleteResponse;
import com.project.event.PlaceEvent;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;

@Service
public class PlaceService implements IPlaceService {

    private static final String CC_CLIENT_ID = "748547722151955";
    private static final String CLIENT_SECRET = "0906127b57b84d3e7e79ad609e33777c";
    private static final String APP_TOKEN = "7b5498718e83abbb4fec8bba2e86073c";

    private static final Logger LOG = LogManager.getLogger(PlaceService.class);

    @Autowired
    private Gson gson;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private PlaceMapper placeMapper;
    
    @Autowired
    private HotelMapper hotelMapper;

    public PlaceMapper getPlaceMapper() {
	return placeMapper;
    }

    public void setPlaceMapper(PlaceMapper placeMapper) {
	this.placeMapper = placeMapper;
    }

    @Override
    public AutocompleteResponse autocompletePlace(String query) {
	List<MyPlace> places = this.getFacebookPlaces(query);
	AutocompleteResponse autocompleteResponse = new AutocompleteResponse();
	autocompleteResponse.setSearchQuery(query);

	if (CollectionUtils.isEmpty(places)) {
	    LOG.warn("::autocompletePlace places have not been found by query : {}", query);
	    autocompleteResponse.setSuccess(false);
	    return autocompleteResponse;
	}

	List<PlaceAutocompleteData> dataList = new ArrayList<PlaceAutocompleteData>();
	autocompleteResponse.setData(dataList);
	for (MyPlace _place : places) {
	    PlaceAutocompleteData data = new PlaceAutocompleteData();
	    dataList.add(data);

	    data.setFbPlaceId(_place.getFbPlaceId());
	    data.setLabel(_place.getName());
	    data.setValue(_place.getFbPlaceId());
	    data.setCoordinate(_place.getCoordinate());
	}

	return autocompleteResponse;
    }

    private List<MyPlace> getFacebookPlaces(String query) {
	AccessToken accessToken = new DefaultFacebookClient(Version.VERSION_3_1).obtainAppAccessToken(CC_CLIENT_ID, CLIENT_SECRET);
	DefaultFacebookClient facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_3_1);
	List<MyPlace> myPlaces = null;
	List<com.restfb.types.Place> places = null;
	try {
	    String categories[] = { "HOTEL_LODGING" };
	    Connection<com.restfb.types.Place> publicSearch = facebookClient.fetchConnection("search", com.restfb.types.Place.class, Parameter.with("q", query),
		    Parameter.with("type", "place"),
		    // Parameter.with("center", "30.559762,36.602792"),
		    // Parameter.with("distance", 4000000),
		    Parameter.with("categories", "[\"HOTEL_LODGING\"]"),
		    Parameter.with("fields", "about, name, category_list, checkins, rating_count, overall_star_rating, location, is_verified"));
	    places = publicSearch.getData();
	    myPlaces = new ArrayList<MyPlace>();
	    for (com.restfb.types.Place _place : places) {
		if (!(_place.getLocation().getCountry().contains("Turkey") || _place.getLocation().getCountry().contains("Türkiye")))
		    continue;
		MyPlace place = new MyPlace();
		place.setFbPlaceId(_place.getId());
		place.setName(_place.getName());
		PointImpl coordinate = new PointImpl(_place.getLocation().getLongitude(), _place.getLocation().getLatitude(), SpatialContext.GEO);
		place.setCoordinate(coordinate);
		myPlaces.add(place);
		System.out.println("---");

	    }

	    PlaceEvent placeEvent = new PlaceEvent(this, myPlaces);
	    applicationEventPublisher.publishEvent(placeEvent);
	    LOG.warn(places.size() + " " + gson.toJson(places));

	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}

	return myPlaces;

    }

	@Override
	public Place findPlaceById(long id) {
		Place place = placeMapper.findPlaceById(id);
		return place;
	}

	@Override
	public List<Place> findAllPlace() {
		List<Place> places = placeMapper.findAllPlace();
		return places;
	}

	@Override
	public Place savePlace(Place place) {
		if (place.getType() == PlaceType.HOTEL_LODGING) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
				Hotel hotel = new Hotel();
				hotel.setName(place.getName());
				
				/** Address **/
				Address address = new Address();
				place.setAddress(address);
				
				/** PlaceId, hotel does not use its id **/
				hotel.setId(place.getId());
				hotelMapper.createHotel(hotel);
			} else {
				Hotel hotel = new Hotel();
				hotel.setName(place.getName());
				
				hotelMapper.updateHotel(hotel);
			}
			
		} else if (place.getType() == PlaceType.AIRPORT) {
			if (place.getId() == 0) {
    			Airport airport = new Airport();
    			airport.setName(place.getName());
			} else {
				
			}
			
		} else if (place.getType() == PlaceType.SHOPPING || place.getType() == PlaceType.EATING_DRINKING) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
			} else {
				placeMapper.updatePlace(place);
			}
		} 
		
		return null;
	}

}
