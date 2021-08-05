package com.project.api.controller.place;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.api.data.enums.LandingPageType;
import com.project.api.data.enums.MainType;
import com.project.api.data.enums.PlaceType;
import com.project.api.data.model.common.Content;
import com.project.api.data.model.event.TimeTable;
import com.project.api.data.model.file.LandingPageFile;
import com.project.api.data.model.file.MyFile;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.PlaceLandingPage;
import com.project.api.data.model.place.PlaceRequest;
import com.project.api.data.service.IFileService;
import com.project.api.data.service.IPlaceService;
import com.project.common.enums.Language;

@RestController
@RequestMapping(value = "/api/v1")
public class PlaceRestController {

	@Autowired
	private Gson gson;

	@Autowired
	IPlaceService placeService;

	@Autowired
	IFileService fileService;

	private static final Logger LOG = LogManager.getLogger(PlaceRestController.class);

	@GetMapping(value = "/places/autocomplete")
	public ResponseEntity<List<Place>> placeAutocomplete(@RequestParam String query) {
		List<Place> places = placeService.autocomplete(query, Language.TURKISH);
		return new ResponseEntity<>(places, HttpStatus.OK);
	}

	@GetMapping(value = "/places/{id}")
	public ResponseEntity<Place> findPlaceById(@PathVariable long id,
			@RequestParam(defaultValue = "RU", required = false) String language) {
		Place place = placeService.findPlaceById(id, language);

		ResponseEntity<Place> responseEntity = new ResponseEntity<>(place, HttpStatus.OK);
		if (LOG.isDebugEnabled()) {
			LOG.debug("::findPlaceById {}", gson.toJson(place));
		}
		return responseEntity;
	}

	@GetMapping(value = "/places")
	public ResponseEntity<List<Place>> findAllPlace(@RequestParam(defaultValue = "RU") String language,
			@RequestParam(required = false, defaultValue = "0") long id,
			@RequestParam(required = false, defaultValue = "1") int type,
			@RequestParam(required = false, defaultValue = "") String types,
			@RequestParam(required = false, defaultValue = "1") int mainType,
			@RequestParam(required = false, defaultValue = "") String mainTypes,
			@RequestParam(required = false, defaultValue = "0") int limit,
			@RequestParam(required = false, defaultValue = "0") int city,
			@RequestParam(required = false, defaultValue = "") String districts,
			@RequestParam(required = false, defaultValue = "") String regions,
			@RequestParam(required = false, defaultValue = "false") boolean random,
			@RequestParam(required = false, defaultValue = "false") boolean hideAddress,
			@RequestParam(required = false, defaultValue = "false") boolean hideContact,
			@RequestParam(required = false, defaultValue = "false") boolean hideContent,
			@RequestParam(required = false, defaultValue = "false") boolean hideImages,
			@RequestParam(required = false, defaultValue = "false") boolean hideMainImage) {
		List<Place> places = null;

		places = placeService.findAllPlaceByFilter(placeRequest(language, id, type, mainType, limit, random, districts,
				regions, hideAddress, hideContact, hideContent, hideImages, hideMainImage));

		if (LOG.isDebugEnabled()) {
			LOG.debug("::findAllPlace {}", gson.toJson(places));
		}

		return new ResponseEntity<>(places, HttpStatus.OK);
	}

	@PostMapping(value = "/places")
	public ResponseEntity<Place> savePlace(RequestEntity<Place> requestEntity) {
		Place place = requestEntity.getBody();
		placeService.savePlace(place, 0);
		if (LOG.isDebugEnabled()) {
			LOG.debug("::savePlace {}", gson.toJson(place));
		}
		return new ResponseEntity<>(place, HttpStatus.OK);
	}

	@GetMapping(value = "/places/{id}/pages")
	public ResponseEntity<PlaceLandingPage> findPageByIdAndLanguage(@PathVariable long id,
			@RequestParam(defaultValue = "RU") String language,
			@RequestParam(required = false, defaultValue = "1") int type,
			@RequestParam(required = false, defaultValue = "1") int mainType,
			@RequestParam(required = false, defaultValue = "0") int limit,
			@RequestParam(required = false, defaultValue = "") String districts,
			@RequestParam(required = false, defaultValue = "") String regions,
			@RequestParam(required = false, defaultValue = "false") boolean random,
			@RequestParam(required = false, defaultValue = "false") boolean hideAddress,
			@RequestParam(required = false, defaultValue = "false") boolean hideContact,
			@RequestParam(required = false, defaultValue = "false") boolean hideContent,
			@RequestParam(required = false, defaultValue = "false") boolean hideImages,
			@RequestParam(required = false, defaultValue = "false") boolean hideMainImage) {
		PlaceLandingPage page = placeService.findLandingPageByFilter(placeRequest(language, id, type, mainType, limit,
				random, districts, regions, hideAddress, hideContact, hideContent, hideImages, hideMainImage));

		if (page == null) {
			page = new PlaceLandingPage();
			Place place = placeService.findPlaceById(id, language);
			page.setPlace(place);
			page.setLanguage(Language.getByCode(language));
			if (place != null) {
				page.setTitle(place.getName());
				page.setPlace(place);
			}
			Content content = new Content();
			List<Content> contents = new ArrayList<>();
			contents.add(content);
			page.setContents(contents);
		}

		return new ResponseEntity<>(page, HttpStatus.OK);
	}

	@PostMapping(value = "/places/pages")
	public ResponseEntity<PlaceLandingPage> saveLandingPage(RequestEntity<PlaceLandingPage> requestEntity) {
		PlaceLandingPage page = requestEntity.getBody();
		placeService.saveLandingPage(page);

		return new ResponseEntity<>(page, HttpStatus.OK);

	}

	@GetMapping(value = "/places/pages")
	public ResponseEntity<List> findAllPagesByFilter(@RequestParam(defaultValue = "RU") String language,
			@RequestParam(required = false, defaultValue = "0") int id,
			@RequestParam(required = false, defaultValue = "1") int type,
			@RequestParam(required = false, defaultValue = "1") int mainType,
			@RequestParam(required = false, defaultValue = "0") int limit,
			@RequestParam(required = false, defaultValue = "") String districts,
			@RequestParam(required = false, defaultValue = "") String regions,
			@RequestParam(required = false, defaultValue = "false") boolean random,
			@RequestParam(required = false, defaultValue = "false") boolean hideAddress,
			@RequestParam(required = false, defaultValue = "false") boolean hideContact,
			@RequestParam(required = false, defaultValue = "false") boolean hideContent,
			@RequestParam(required = false, defaultValue = "false") boolean hideImages,
			@RequestParam(required = false, defaultValue = "false") boolean hideMainImage) {

		List<PlaceLandingPage> pages = placeService
				.findAllLandingPageByFilter(placeRequest(language, id, type, mainType, limit, random, districts, regions,
						hideAddress, hideContact, hideContent, hideImages, hideMainImage));
		return new ResponseEntity<>(pages, HttpStatus.OK);
	}

	private PlaceRequest placeRequest(String language, long id, int type, int mainType, int limit, boolean random,
			String districts, String regions, boolean hideAddress, boolean hideContact, boolean hideContent,
			boolean hideImages, boolean hideMainImage) {
		PlaceRequest placeRequest = new PlaceRequest();

		placeRequest.setId(id);

		if (type > 1) {
			placeRequest.setType(PlaceType.getById(type));
		}
		if (mainType > 1) {
			placeRequest.setMainType(MainType.getById(mainType));
		}
		if (limit > 0) {
			placeRequest.setLimit(limit);
		}
		if (!districts.isBlank()) {
			placeRequest.setDistricts(districts.split(","));
		}
		if (!regions.isBlank()) {
			placeRequest.setRegions(regions.split(","));
		}
		if (random) {
			placeRequest.setRandom(Boolean.TRUE);
		}
		if (hideAddress) {
			placeRequest.setHideAddress(Boolean.TRUE);
		}
		if (hideContact) {
			placeRequest.setHideContact(Boolean.TRUE);
		}
		if (hideContent) {
			placeRequest.setHideContent(Boolean.TRUE);
		}
		if (hideImages) {
			placeRequest.setHideImages(Boolean.TRUE);
		}
		if (hideMainImage) {
			placeRequest.setHideMainImage(Boolean.TRUE);
		}
		//

		placeRequest.setLanguage(Language.getByCode(language));
		return placeRequest;
	}

	@GetMapping(value = "/places/{id}/files")
	public ResponseEntity<List> findPageByIdAndLanguage(@PathVariable long id) {
		List<MyFile> images = fileService.getFilesByPageId(LandingPageType.PLACE.getId(), id);
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	@GetMapping(value = "/places/files")
	public ResponseEntity<List> findFiles() {
		List<LandingPageFile> images = fileService.getFiles();
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	@PostMapping(value = "/places/{id}/main-image")
	public ResponseEntity<Boolean> setMainPage(@PathVariable long id, RequestEntity<Long> requestEntity) {
		long fileId = requestEntity.getBody();
		placeService.setMainImage(id, fileId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping(value = "/places/{id}/time-table")
	public ResponseEntity<List> getTimeTableByEventId(@PathVariable long id) {
		List<TimeTable> timetable = placeService.getTimeTableByPlaceId(id);
		return new ResponseEntity<>(timetable, HttpStatus.OK);
	}

	@PostMapping(value = "/places/time-table")
	public ResponseEntity<Integer> saveTimeTable(RequestEntity<TimeTable> requestEntity) {
		int result = placeService.saveTimeTable(requestEntity.getBody());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/places/time-table/{id}")
	public ResponseEntity<Integer> deleteTimeTableById(@PathVariable long id) {
		int result = placeService.deleteTimeTableById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
