package com.vantalii.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.model.hotel.Hotel;
import com.vantalii.data.service.IHotelService;
import com.vantalii.data.service.IPlaceService;

@RestController
@RequestMapping(value = "/api/v1/")
public class HotelRestController {

	@Autowired
	IHotelService hotelService;
	
	@Autowired
	IPlaceService placeService;

	@RequestMapping(value = "/hotels", method = RequestMethod.GET)
	public ResponseEntity<List<Hotel>> getHotels() {
		List<Hotel> hotels = hotelService.findAllHotel();
		
//		for (Hotel hotel : hotels) {
//			Place place = new Place();
//			place.setName(hotel.getName());
//			place.setType(PlaceType.HOTEL);
//			place.setLanguage(Language.RUSSIAN);
//			Map<String, Localisation> localisation = new HashMap<String, Localisation>();
//			
//			localisation.put(Language.TURKISH.toString(), new Localisation(hotel.getName(), Language.TURKISH));
//			localisation.put(Language.ENGLISH.toString(), new Localisation(hotel.getName(), Language.ENGLISH));
//			place.setLocalisation(localisation);
//			Address address = new Address();
//			address.setRegionId(hotel.getRegionId());
//			if (hotel.getLat() != null && hotel.getLng() !=null) {
//				address.setLat(Double.parseDouble(hotel.getLat().replace(",", ".")));
//				address.setLng(Double.valueOf(hotel.getLng().replace(",", ".")));
//			}
//			place.setAddress(address);
//			placeService.savePlace(place, hotel.getStar());
//		}
//		
		ResponseEntity<List<Hotel>> responseEntity = new ResponseEntity<List<Hotel>>(hotels, HttpStatus.OK);

		return responseEntity;
	}
}
