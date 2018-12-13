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
import com.project.api.data.enums.MainType;
import com.project.api.data.enums.PlaceType;
import com.project.api.data.enums.Star;
import com.project.api.data.mapper.AirportMapper;
import com.project.api.data.mapper.HotelMapper;
import com.project.api.data.mapper.PlaceMapper;
import com.project.api.data.model.common.Address;
import com.project.api.data.model.common.MyPlace;
import com.project.api.data.model.flight.Airport;
import com.project.api.data.model.hotel.Hotel;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.PlaceAutocompleteData;
import com.project.api.data.model.place.RestaurantCafe;
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

    @Autowired
    private AirportMapper airportMapper;
    
  

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
	public Place findPlaceById(long id,String language, String originalLanguage) {
		Place place = placeMapper.findPlaceById(id,language, originalLanguage);
		return place;
	}

	@Override
	public List<Place> findAllPlace(String language, String originalLanguage) {
		List<Place> places = placeMapper.findAllPlace(language, originalLanguage);
		return places;
	}

	@Override
	public Place savePlace(Place place) {
		/** Address **/
		Address address = place.getAddress();
		if (place.getId() == 0 || (place.getAddress() != null && place.getAddress().getId() == 0)) {
			placeMapper.createPlaceAddress(address);
		} else if (place.getId() != 0 && (place.getAddress() != null && place.getAddress().getId() != 0)) {
			placeMapper.updatePlaceAddress(address);
		} else {
			LOG.error("::savePlace PlaceId and AddressId are not defined!..");
		}
		/** END of Address **/ 
		
		if (place.getType() == PlaceType.HOTEL) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
				Hotel hotel = new Hotel();
				hotel.setName(place.getName());
				hotel.setOriginalName(place.getOriginalName());
				hotel.setStar(Star.NOTSET);

				
				/** PlaceId, hotel does not use its id **/
				hotel.setId(place.getId());
				hotelMapper.createHotel(hotel);
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("Hotel (Place) has been created. hotel: {}", gson.toJson(hotel));
				}
			} else {
				placeMapper.updatePlace(place);
			}
		} else if (place.getType() == PlaceType.AIRPORT) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);

    			Airport airport = new Airport();
    			airport.setName(place.getName());
    			airport.setLanguage(place.getLanguage());
    			airport.setOriginalName(place.getOriginalName());
    			airport.setOriginalLanguage(place.getOriginalLanguage());
    			
    			
    			/** PlaceId, airport does not use its id **/
    			airport.setId(place.getId());
    			airportMapper.createAirport(airport);
    			
    			if (LOG.isDebugEnabled()) {
					LOG.debug("Airport (Place) has been created. airport: {}", gson.toJson(airport));
				}
			} else {
				placeMapper.updatePlace(place);
			}
			
		} else if (place.getType().getMainType() == MainType.SHOPPING) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
			} else {
				placeMapper.updatePlace(place);
			}
		} else if (place.getType().getMainType() == MainType.FOOD_AND_BEVERAGE) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
				
				RestaurantCafe restaurantCafe = new RestaurantCafe();
				restaurantCafe.setName(place.getName());
				restaurantCafe.setLanguage(place.getLanguage());
				restaurantCafe.setOriginalName(place.getName());
				restaurantCafe.setOriginalLanguage(place.getOriginalLanguage());
				
    			/** PlaceId, restaurant/Cafe does not use its id **/
				restaurantCafe.setId(place.getId());
				placeMapper.createRestaurantCafe(restaurantCafe);
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("Restaurant/Cafe (Place) has been created. restaurant/cafe: {}", gson.toJson(restaurantCafe));
				}
			} else {
				placeMapper.updatePlace(place);
			}
		} else {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
			} else {
				placeMapper.updatePlace(place);
			}
		}
		
		/** PlaceName SAVE--UPDATE **/
		if (place.getOriginalLanguage().equals(place.getLanguage())) {
			placeMapper.savePlaceName(place.getOriginalName(), place.getOriginalLanguage().getCode(), place.getId());
		} else {
			if (place.getLanguage() != null) {
				placeMapper.savePlaceName(place.getName(), place.getLanguage().getCode(), place.getId());
			}
			if (place.getOriginalLanguage() != null) {
				placeMapper.savePlaceName(place.getOriginalName(), place.getOriginalLanguage().getCode(), place.getId());
			}
		}
		/** END of PlaceName SAVE--UPDATE **/
		
		return null;
	}

}
