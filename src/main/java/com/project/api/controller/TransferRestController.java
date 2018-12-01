package com.project.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
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

	
	@RequestMapping(value = "/transfers/places/{id}", method = RequestMethod.GET)
	public ResponseEntity<Place> findPlaceById(@PathVariable long id) {
		Place place = placeService.findPlaceById(id);
		ResponseEntity<Place> responseEntity = new ResponseEntity<Place>(place, HttpStatus.OK);
		if (LOG.isDebugEnabled()) {
			LOG.debug("::findPlaceById {}" , gson.toJson(place));
		}
		return responseEntity;
	}
	
	@RequestMapping(value = "/transfers/places", method = RequestMethod.GET)
	public ResponseEntity<List<Place>> findAllPlace(@RequestParam(defaultValue = "RU", required=false) String language, @RequestParam(defaultValue = "TR", required=false) String originalLanguage) {
		List<Place> places = placeService.findAllPlace(language, originalLanguage);
		if (LOG.isDebugEnabled()) {
			LOG.debug("::findAllPlace {}" , gson.toJson(places));
		}
//		Place place = placeService.findPlaceById(id);
		ResponseEntity<List<Place>> responseEntity = new ResponseEntity<List<Place>>(places, HttpStatus.OK);

		return responseEntity;
	}
	
	@RequestMapping(value = "/transfers/places", method = RequestMethod.POST)
	public ResponseEntity<Place> savePlace(RequestEntity<Place> requestEntity) {
		Place place= requestEntity.getBody();
		placeService.savePlace(place);
		if (LOG.isDebugEnabled()) {
			LOG.debug("::savePlace {}" , gson.toJson(place));
		}
		ResponseEntity<Place> responseEntity = new ResponseEntity<Place>(place, HttpStatus.OK);

		return responseEntity;
	}

}
