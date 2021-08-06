package com.vantalii.data.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.project.api.data.enums.LandingPageType;
import com.project.api.data.enums.MainType;
import com.project.api.data.enums.PlaceType;
import com.project.api.data.enums.Star;
import com.project.api.data.model.Facility;
import com.project.api.data.model.common.Address;
import com.project.api.data.model.common.Contact;
import com.project.api.data.model.common.Content;
import com.project.api.data.model.event.TimeTable;
import com.project.api.data.model.file.MyFile;
import com.project.api.data.model.flight.Airport;
import com.project.api.data.model.gis.enums.CityEnum;
import com.project.api.data.model.gis.enums.DistrictEnum;
import com.project.api.data.model.gis.enums.RegionEnum;
import com.project.api.data.model.hotel.Hotel;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.PlaceLandingPage;
import com.project.api.data.model.place.PlaceRequest;
import com.project.api.data.model.place.RestaurantCafe;
import com.project.api.data.utils.MyBatisUtils;
import com.project.common.enums.Language;
import com.vantalii.api.data.mapper.AirportMapper;
import com.vantalii.api.data.mapper.HotelMapper;
import com.vantalii.api.data.mapper.PlaceMapper;
import com.vantalii.api.data.mapper.TimeTableMapper;
import com.vantalii.api.utils.ApiUtils;
import com.vantalii.data.mybatis.entity.PlaceEntity;
import com.vantalii.data.service.IFileService;
import com.vantalii.data.service.IPlaceService;

@Service
public class PlaceService implements IPlaceService {

	private static final Logger LOG = LogManager.getLogger(PlaceService.class);

	@Autowired
	private Gson gson;

	@Autowired
	private PlaceMapper placeMapper;

	@Autowired
	private HotelMapper hotelMapper;

	@Autowired
	private TimeTableMapper timeTableMapper;

	@Autowired
	private AirportMapper airportMapper;

	@Autowired
	private IFileService fileService;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public Place findPlaceById(long id, String language) {
		Place place = null;
		try {
			place = placeMapper.findPlaceById(id, language);
			List<Localisation> names = placeMapper.findAllPlaceNameByPlaceId(place.getId());

			HashMap<String, Localisation> localisation = new HashMap<>();

			for (Localisation name : names) {
				localisation.put(name.getLanguage().toString(), name);
			}

			if (place.getAddress() != null && place.getAddress().getId() > 0) {
				Address address = placeMapper.findAddressById(place.getAddress().getId());
				if (address != null) {
					place.setAddress(fillDataWithEnums(address, Language.getByCode(language)));
				}
			}

			place.setContact(placeMapper.findContactByPlaceId(id));

			place.setImages(fileService.getFilesByPageId(LandingPageType.PLACE.getId(), id));

			place.setLocalisation(localisation);
		} catch (Exception e) {
			LOG.error("::findPlaceById({}) - {}", id, e);
		}

		return place;
	}

	@Override
	public List<Place> findAllPlace(String language) {
		List<Place> places = placeMapper.findAllPlace(language);

		if (places != null && !places.isEmpty()) {
			for (Place place : places) {
				List<Localisation> names = placeMapper.findAllPlaceNameByPlaceId(place.getId());
				Map<String, Localisation> localisation = new HashMap<>();
				for (Localisation name : names) {
					if (name != null && name.getLanguage() != null)
						localisation.put(name.getLanguage().toString(), name);
				}
				place.setLocalisation(localisation);

			}
		}

		return places;
	}

	@Override
	public Place savePlace(Place place, int star) {
		/** Address **/
		Address address = place.getAddress();

		if (address != null && address.getRegionId() != 0 && address.getSubregionId() != 0) {
			address.setRegionId(address.getSubregionId());
		}

		if (place.getId() == 0 || (place.getAddress() != null && place.getAddress().getId() == 0)) {
			placeMapper.createPlaceAddress(address);
		}
		if (place.getId() != 0 && (place.getAddress() != null && place.getAddress().getId() != 0)) {
			placeMapper.updatePlaceAddress(address);
		} else {
			LOG.error("::savePlace PlaceId and AddressId are not defined!..");
		}
		/** END of Address **/

		/** **/

		if (place.getId() != 0) {
			place.setSlug(ApiUtils.generateSlug(place.getName(), place.getId()));
			if (LOG.isDebugEnabled()) {
				LOG.debug("::updatePlace place: {}", gson.toJson(place));
			}
			placeMapper.updatePlace(place);
		}

		if (place.getType() == PlaceType.HOTEL) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
				Hotel hotel = new Hotel();
				hotel.setName(place.getName());
				hotel.setStar(Star.getById(star));

				/** PlaceId, hotel does not use its id **/
				hotel.setId(place.getId());
				hotelMapper.createHotel(hotel);

				if (LOG.isDebugEnabled()) {
					LOG.debug("Hotel (Place) has been created. hotel: {}", gson.toJson(hotel));
				}
			}
		} else if (place.getType() == PlaceType.AIRPORT) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);

				Airport airport = new Airport();
				airport.setName(place.getName());
				airport.setLanguage(place.getLanguage());

				/** PlaceId, airport does not use its id **/
				airport.setId(place.getId());
				airportMapper.createAirport(airport);

				if (LOG.isDebugEnabled()) {
					LOG.debug("Airport (Place) has been created. airport: {}", gson.toJson(airport));
				}
			}
		} else if (place.getType().getMainType() == MainType.SHOPPING) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
			}
		} else if (place.getType().getMainType() == MainType.FOOD_AND_BEVERAGE) {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);

				RestaurantCafe restaurantCafe = new RestaurantCafe();
				restaurantCafe.setName(place.getName());
				restaurantCafe.setLanguage(place.getLanguage());

				/** PlaceId, restaurant/Cafe does not use its id **/
				restaurantCafe.setId(place.getId());
				placeMapper.createRestaurantCafe(restaurantCafe);

				if (LOG.isDebugEnabled()) {
					LOG.debug("Restaurant/Cafe (Place) has been created. restaurant/cafe: {}",
							gson.toJson(restaurantCafe));
				}
			}
		} else {
			if (place.getId() == 0) {
				placeMapper.createPlace(place);
			}
		}

		/** PlaceName/Slug SAVE--UPDATE **/
		if (place.getLanguage() != null) {
			placeMapper.savePlaceName(place.getName(), place.getLanguage().getCode(), place.getId(),
					ApiUtils.generateSlug(place.getName(), place.getId()));
		}
		if (place.getLocalisation() != null) {
			place.getLocalisation().entrySet().stream().filter(
					l -> (l.getValue() != null && l.getValue().getName() != null && !l.getValue().getName().isEmpty()))
					.forEach(e -> placeMapper.savePlaceName(e.getValue().getName(),
							Language.valueOf(e.getKey()).getCode(), place.getId(),
							ApiUtils.generateSlug(e.getValue().getName(), place.getId())));
			// placeMapper.savePlaceName(e.getValue().getName(),
			// e.getValue().getLanguage().getCode(), place.getId())

		}

		/** END of PlaceName SAVE--UPDATE **/
		/** Contact **/
		Contact contact = place.getContact();

		if (place.getId() == 0 || (contact != null && (contact.getId() == 0 || !contact.getPhone().isBlank()
				|| !contact.getCallCenter().isBlank() || !contact.getWeb().isBlank() || !contact.getEmail().isBlank()
				|| !contact.getWhatsapp().isBlank()))) {
			placeMapper.savePlaceContact(contact, place.getId());
		} else {
			LOG.error("::savePlaceContact PlaceId and ContactId are not defined!..");
		} /** END of Contact **/
		return null;
	}

	@Override
	public PlaceLandingPage findLandingPageByPlaceIdAndLanguage(long id, String language) {
		PlaceRequest placeRequest = new PlaceRequest();
		placeRequest.setId(id);
		placeRequest.setLanguage(Language.getByCode(language));

		List<PlaceLandingPage> landingPages = this.findAllLandingPageByFilter(placeRequest);

		if (landingPages == null || landingPages.isEmpty()) {
			return null;
		}
		return landingPages.get(0);
	}

	@Override
	public void saveLandingPage(PlaceLandingPage page) {
		if (page != null) {
			placeMapper.saveLandingPage(page);
			if (page.getContents() != null && !page.getContents().isEmpty()) {

				if (page.getId() > 0 && page.getContents().get(0).getId() == 0) {
					placeMapper.insertContents(page.getId(), page.getContents());
				} else if (page.getId() > 0 && page.getContents().get(0).getId() != 0) {
					placeMapper.updateContents(page.getContents());
				}

			}
		}
	}

	@Override
	public List<Place> findAllPlaceByType(String language, PlaceType type) {
		List<Place> places = placeMapper.findAllPlaceByType(language, type.getId());

		if (places != null && !places.isEmpty()) {
			for (Place place : places) {
				List<Localisation> names = placeMapper.findAllPlaceNameByPlaceId(place.getId());
				Map<String, Localisation> localisation = new HashMap<>();
				for (Localisation name : names) {
					if (name != null && name.getLanguage() != null) {
						localisation.put(name.getLanguage().toString(), name);
					}
				}
				place.setLocalisation(localisation);

			}
		}
		return places;

	}

	@Override
	public List<Place> findAllPlaceByMainType(String language, MainType mainType) {
		List<Place> places = placeMapper.findAllPlaceByMainType(language, getTypesAsStringByMainType(mainType));

		if (places != null && !places.isEmpty()) {
			for (Place place : places) {
				List<Localisation> names = placeMapper.findAllPlaceNameByPlaceId(place.getId());
				Map<String, Localisation> localisation = new HashMap<>();
				for (Localisation name : names) {
					if (name != null && name.getLanguage() != null) {
						localisation.put(name.getLanguage().toString(), name);
					}
				}
				place.setLocalisation(localisation);

			}
		}

		return places;
	}

	@Override
	public List<Place> autocomplete(String name, Language language) {
		return placeMapper.autocomplete(name, language.getCode());
	}

	@Override
	public List<PlaceLandingPage> findAllLandingPageByFilter(PlaceRequest placeRequest) {
		List<PlaceLandingPage> pages = placeMapper.findAllLandingPageByFilter(placeRequest,
				getTypesByMainType(placeRequest.getMainType()));
		if (!CollectionUtils.isEmpty(pages)) {
			for (int i = 0; i < pages.size(); i++) {

				if (pages.get(i) != null && pages.get(i).getPlace() != null && pages.get(i).getPlace().getId() > 0) {
					Place place = this.findPlaceById(pages.get(i).getPlace().getId(),
							pages.get(i).getLanguage().getCode());
					if (place == null) {
						continue;
					}
					if (!placeRequest.isHideAddress() && place.getAddress() != null && place.getAddress().getId() > 0) {
						Address address = placeMapper.findAddressById(place.getAddress().getId());
						if (address != null) {
							place.setAddress(fillDataWithEnums(address, placeRequest.getLanguage()));
						}
					} else {
						place.setAddress(null);
					}

					if (!placeRequest.isHideContent()) {
						List<Content> contents = placeMapper.findAllContentByPageId(place.getId());
						if (contents != null) {
							pages.get(i).setContents(contents);
						}
					}

					if (!placeRequest.isHideContact() && place.getId() > 0) {
						Contact contact = placeMapper.findContactByPlaceId(place.getId());
						place.setContact(contact);

					} else {
						place.setContact(null);
					}

					if (!placeRequest.isHideImages() && place.getId() > 0) {
						List<MyFile> images = placeMapper.findAllImagesByPlaceId(place.getId());
						place.setImages(images);
					} else {
						place.setImages(Collections.emptyList());
					}

					if (!placeRequest.isHideMainImage() && place.getMainImage() != null
							&& place.getMainImage().getId() > 0) {
						MyFile mainImage = placeMapper.findMainImage(place.getMainImage().getId());
						place.setMainImage(mainImage);
					}

					pages.get(i).setPlace(place);

//					List<TimeTable> timeTable = timeTableMapper.findAllTimeTableByPlaceId(place.getId());
//					
//					if (timeTable != null && !timeTable.isEmpty()) {}

				}
			}
		}

		return pages;

	}

	private Address fillDataWithEnums(Address address, Language language) {
		if (address.getRegionId() > 0 && RegionEnum.getById(address.getRegionId()) != null) {
			RegionEnum regionEnum = RegionEnum.getById(address.getRegionId());
			address.setRegionId(regionEnum.getId());
			if (language.getCode().equalsIgnoreCase(Language.TURKISH.getCode())) {
				address.setRegion(regionEnum.getName());
			} else {
				address.setRegion(regionEnum.getEnName());
			}
			address.setDistrictId(regionEnum.getDistrictEnum().getId());
			address.setDistrict(regionEnum.getDistrictEnum().getName());
			address.setCityId(regionEnum.getDistrictEnum().getCityEnum().getId());
			address.setCity(regionEnum.getDistrictEnum().getCityEnum().getName());
		} else if (address.getDistrictId() > 0 && DistrictEnum.getById(address.getDistrictId()) != null) {
			DistrictEnum districtEnum = DistrictEnum.getById(address.getDistrictId());
			address.setDistrictId(districtEnum.getId());
			address.setDistrict(districtEnum.getName());
			address.setCityId(districtEnum.getCityEnum().getId());
			address.setCity(districtEnum.getCityEnum().getName());
		} else if (address.getCityId() > 0 && CityEnum.getById(address.getCityId()) != null) {
			CityEnum cityEnum = CityEnum.getById(address.getCityId());
			address.setCityId(cityEnum.getId());
			address.setCity(cityEnum.getName());
		} else {
			LOG.warn("::findAddressById({}) incomplete data", address.getId());
		}
		return address;
	}

	private String getTypesAsStringByMainType(MainType mainType) {
		List<String> ids = null;
		if (mainType != null && mainType != MainType.NOTSET) {
			ids = new ArrayList<>();
			for (PlaceType type : PlaceType.values()) {
				if (type.getMainType() == mainType) {
					ids.add(String.valueOf(type.getId()));
				}
			}
		}

		if (ids != null) {
			return MyBatisUtils.inStatement(ids);
		}
		return null;
	}

	@Override
	public boolean setMainImage(long id, long fileId) {
		placeMapper.setMainImage(id, fileId);
		return false;
	}

	@Override
	public int deleteTimeTableById(long id) {
		return timeTableMapper.deleteTimeTableById(id);
	}

	@Override
	public int saveTimeTable(TimeTable timeTable) {
		return timeTableMapper.saveTimeTable(timeTable);
	}

	@Override
	public List<TimeTable> getTimeTableByPlaceId(long id) {
		return timeTableMapper.findAllTimeTableByPlaceId(id);
	}

	@Override
	public List<Place> findAllPlaceByFilter(PlaceRequest placeRequest) {
		List<PlaceEntity> placeEntites = placeMapper.findAllPlaceByFilter(placeRequest,
				getTypesByMainType(placeRequest.getMainType()));
		List<Place> places = new ArrayList<>();

		if (!CollectionUtils.isEmpty(placeEntites)) {
			for (PlaceEntity placeEntity : placeEntites) {
				Place place = modelMapper.map(placeEntity, Place.class);
				List<Localisation> names = placeMapper.findAllPlaceNameByPlaceId(placeEntity.getId());
				Map<String, Localisation> localisation = new HashMap<>();
				for (Localisation name : names) {
					if (name != null && name.getLanguage() != null) {
						localisation.put(name.getLanguage().toString(), name);
					}
				}
				place.setLocalisation(localisation);

			}
		}

		return places;
	}

	@Override
	public int savePlaceFacilities(long id, String facilitiesJson) {
		placeMapper.savePlaceFacilities(id, facilitiesJson);
		return 0;
	}

	@Override
	public List<Facility> getPlaceFacilitiesByPlaceId(long id) {
		String facilitiesJson = placeMapper.findPlaceFacilities(id);
		if (facilitiesJson != null) {
			return gson.fromJson(facilitiesJson, List.class);
		}
		return Collections.emptyList();
	}

	@Override
	public PlaceLandingPage findLandingPageByFilter(PlaceRequest placeRequest) {
		List<PlaceLandingPage> pages = this.findAllLandingPageByFilter(placeRequest);
		if (!CollectionUtils.isEmpty(pages) && pages.size() == 1) {
			return pages.get(0);
		}

		return null;
	}

	private List<Integer> getTypesByMainType(MainType mainType) {
		List<Integer> ids = Collections.emptyList();
		if (mainType != null && mainType != MainType.NOTSET) {
			ids = new ArrayList<>();
			for (PlaceType type : PlaceType.values()) {
				if (type.getMainType() == mainType) {
					ids.add(type.getId());
				}
			}
		}

		return ids;
	}

}
