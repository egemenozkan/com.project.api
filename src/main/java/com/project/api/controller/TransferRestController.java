package com.project.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.api.data.enums.AddressType;
import com.project.api.data.enums.Language;
import com.project.api.data.enums.PlaceType;
import com.project.api.data.enums.Star;
import com.project.api.data.model.common.Address;
import com.project.api.data.model.hotel.Hotel;
import com.project.api.data.model.place.Place;
import com.project.api.data.service.IPlaceService;
import com.project.common.model.AutocompleteResponse;

@RestController
@RequestMapping(value = "/api/v1/test/")
public class TransferRestController {

	@Autowired
	private Gson gson;

	@Autowired
	IPlaceService placeService;

	private static final Logger LOG = LogManager.getLogger(TransferRestController.class);

	// @RequestMapping(value = "/transfers/autocomplete", method =
	// RequestMethod.GET)
	@RequestMapping(value = "/transfers/autocomplete", method = RequestMethod.GET)
	public ResponseEntity<AutocompleteResponse> placeAutocomplete(@RequestParam String query) {
		AutocompleteResponse autocompleteResponse = placeService.autocompletePlace(query);
		ResponseEntity<AutocompleteResponse> response = new ResponseEntity<AutocompleteResponse>(autocompleteResponse, HttpStatus.OK);

		return response;
	}

	@RequestMapping(value = "/transfers/places", method = RequestMethod.GET)
	public ResponseEntity<List<Place>> findAllPlace() {
		List<Place> places = placeService.findAllPlace();
		ResponseEntity<List<Place>> response = new ResponseEntity<List<Place>>(places, HttpStatus.OK);

		return response;
	}
	
	@RequestMapping(value = "/transfers/places/{id}", method = RequestMethod.GET)
	public ResponseEntity<Place> findPlaceById(@PathVariable long id) {
		Place place = placeService.findPlaceById(id);
		ResponseEntity<Place> response = new ResponseEntity<Place>(place, HttpStatus.OK);

		return response;
	}
	
	@RequestMapping(value = "/transfers/places2", method = RequestMethod.GET)
	public ResponseEntity<Place> savePlace() {
		
		Hotel place = new Hotel();
		
		place.setStar(Star.STAR_5);
		
		place.setId(20L);
		place.setType(PlaceType.HOTEL_LODGING);
		place.setName("Test Hotel RU");
		place.setLanguage(Language.RUSSIAN);
		place.setOriginalName("Test Otel TR");
		place.setOriginalLanguage(Language.TURKISH);
		
		/** Address Save **/
		Address address = new Address();
		address.setId(14);
		address.setAddressLine1("Bla bla bla");
		address.setAddressLine2("address line 2");
		address.setAddressTitle("Adres Başlık Deneme");
		address.setType(AddressType.NOTSET);
		address.setRegionId(1);
		address.setCityId(1);
		address.setLat(35.3);
		address.setLng(33.4);
		place.setAddress(address);
		/** END of Address Save **/
		
		placeService.savePlace(place);
		LOG.debug("::place {}" , gson.toJson(place));
//		Place place = placeService.findPlaceById(id);
//		ResponseEntity<Place> response = new ResponseEntity<Place>(place, HttpStatus.OK);

		return null;
	}

}
