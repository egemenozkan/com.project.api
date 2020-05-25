package com.project.api.controller.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.api.data.enums.LandingPageType;
import com.project.api.data.enums.ProductType;
import com.project.api.data.model.autocomplete.AutocompleteResponse;
import com.project.api.data.model.autocomplete.Item;
import com.project.api.data.model.event.Event;
import com.project.api.data.model.event.EventLandingPage;
import com.project.api.data.model.event.EventRequest;
import com.project.api.data.model.event.EventStatus;
import com.project.api.data.model.event.EventType;
import com.project.api.data.model.event.TimeTable;
import com.project.api.data.model.file.LandingPageFile;
import com.project.api.data.model.file.MyFile;
import com.project.api.data.service.IEventService;
import com.project.api.data.service.IFileService;
import com.project.api.data.service.IPlaceService;
import com.project.common.enums.Language;

@RestController
@RequestMapping(value = "/api/v1/")
public class EventRestController {

	@Autowired
	private Gson gson;

	@Autowired
	private IEventService eventService;
	@Autowired
	private IPlaceService placeService;
	@Autowired
	private IFileService fileService;

	private static final Logger LOG = LogManager.getLogger(EventRestController.class);

	private static final int AUTOCOMPLETE_MIN_CHAR = 3;

	@GetMapping(value = "/events")
	public ResponseEntity<List<Event>> getEvents(@RequestParam(defaultValue = "RU") String language,
			@RequestParam(required = false, defaultValue = "1") int type,
			@RequestParam(required = false, defaultValue = "") String types,
			@RequestParam(required = false, defaultValue = "0") int limit,
			@RequestParam(required = false, defaultValue = "false") boolean random,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate startDate,
			@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate endDate,
			@RequestParam(required = false, defaultValue = "false") boolean distinct,
			@RequestParam(required = false, defaultValue = "0") int timeTableId,
			@RequestParam(required = false, defaultValue = "false") boolean hidePlace,
			@RequestParam(required = false) String name,
			@RequestParam(required = false, defaultValue = "") String districts,
			@RequestParam(required = false, defaultValue = "") String regions,
			@RequestParam(required = false, defaultValue = "1") int status) {

		EventRequest eventRequest = new EventRequest();
		if (type > 1) {
			eventRequest.setType(EventType.getById(type));
		}

		if (types != null && !types.isBlank()) {
			eventRequest.setTypes(types.split(","));
		}
		if (limit > 0) {
			eventRequest.setLimit(limit);
		}
		if (random) {
			eventRequest.setRandom(Boolean.TRUE);
		}
		if (startDate != null) {
			eventRequest.setStartDate(startDate);
		}
		if (endDate != null) {
			eventRequest.setEndDate(endDate);
		}
		if (timeTableId > 0) {
			eventRequest.setTimeTableId(timeTableId);
		}
		if (hidePlace) {
			eventRequest.setHidePlace(hidePlace);
		}
		if (name != null && !name.isBlank() && name.length() >= 3) {
			eventRequest.setName(name);
		}
		eventRequest.setStatus(EventStatus.getById(status));

		eventRequest.setLanguage(Language.getByCode(language));

		List<Event> events = eventService.getEvents(eventRequest);
		
		

		if (distinct && !CollectionUtils.isEmpty(events)) {
			Iterator<Event> itr = events.iterator();
			Event previous = itr.next();
			while (itr.hasNext()) {
				Event next = itr.next();

				if (previous.getId() == next.getId()) {
					itr.remove();
				}
				previous = next;
			}
		}

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	@GetMapping(value = "/events/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable long id,
			@RequestParam(defaultValue = "RU", required = false) String language,
			@RequestParam(required = false, defaultValue = "0") int timeTableId) {

		Event event = eventService.getEventById(id, Language.getByCode(language), timeTableId);

		if (event != null && event.getPlace() != null && event.getPlace().getId() > 0) {
			event.setPlace(placeService.findPlaceById(event.getPlace().getId(), language));
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("::getEventById {}", gson.toJson(event));
		}

		return new ResponseEntity<>(event, HttpStatus.OK);
	}

	@GetMapping(value = "/events/autocomplete")
	public ResponseEntity<AutocompleteResponse> autocompleteEvents(@RequestParam(defaultValue = "RU") String language,
			@RequestParam(required = false, name = "query") String name,
			@RequestParam(required = false, name = "status", defaultValue = "1") int status) {
		List<Event> events = null;

		AutocompleteResponse autocompleteResponse = new AutocompleteResponse();
		if (name == null || name.isBlank() || name.length() < AUTOCOMPLETE_MIN_CHAR) {
			autocompleteResponse.setSuccess(Boolean.FALSE);
			autocompleteResponse.setItems(Collections.emptyList());
			autocompleteResponse.setErrorMessage("MinChars");
			return new ResponseEntity<>(autocompleteResponse, HttpStatus.NOT_ACCEPTABLE);
		}
		
		EventRequest eventRequest = new EventRequest();
		eventRequest.setHidePlace(Boolean.TRUE);
		eventRequest.setLanguage(Language.getByCode(language));
		eventRequest.setStatus(EventStatus.getById(status));
		eventRequest.setName(name);
		events = eventService.getEvents(eventRequest);

		if (!CollectionUtils.isEmpty(events)) {
			Iterator<Event> itr = events.iterator();
			Event previous = itr.next();
			while (itr.hasNext()) {
				Event next = itr.next();

				if (previous.getId() == next.getId()) {
					itr.remove();
				}
				previous = next;
			}
		}

		if (!CollectionUtils.isEmpty(events)) {
			autocompleteResponse.setSuccess(Boolean.TRUE);
			List<Item> items = new ArrayList<>();
			for (Event event : events) {
				StringBuilder strBuilder = new StringBuilder("/");
				String prefix = "events/";

				if (eventRequest.getLanguage() != Language.RUSSIAN) {
					strBuilder.append(eventRequest.getLanguage().toString().toLowerCase()).append("/");
				}

				strBuilder.append(prefix).append(event.getSlug());
				items.add(new Item(event.getId(), event.getName(), strBuilder.toString(), ProductType.EVENT));
			}
			autocompleteResponse.setItems(items);

		} else {
			autocompleteResponse.setSuccess(Boolean.FALSE);
			autocompleteResponse.setItems(Collections.emptyList());
		}

		return new ResponseEntity<>(autocompleteResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/events")
	public ResponseEntity<Event> savePlace(RequestEntity<Event> requestEntity) {
		Event event = requestEntity.getBody();
		eventService.saveEvent(event);
		if (LOG.isDebugEnabled()) {
			LOG.debug("::saveEvent {}", gson.toJson(event));
		}

		return new ResponseEntity<>(event, HttpStatus.OK);
	}

	@GetMapping(value = "/events/{id}/pages")
	public ResponseEntity<EventLandingPage> findLandingPageByEventId(@PathVariable long id,
			@RequestParam(defaultValue = "RU", required = false) String language,
			@RequestParam(required = false, defaultValue = "0") int timeTableId) {

		EventRequest eventRequest = new EventRequest();
		eventRequest.setId(id);
		eventRequest.setLanguage(Language.getByCode(language));

		if (timeTableId > 0) {
			eventRequest.setTimeTableId(timeTableId);
		}

		EventLandingPage landingPage = eventService.findLandingPageByEventId(eventRequest);
		if (landingPage == null) {
			LOG.warn("::findLandingPageByEventId IsNULL eventId: {}, language: {}", id, language);
		}

		return new ResponseEntity<>(landingPage, HttpStatus.OK);
	}

	// @GetMapping(value = "/events/pages")
	// public ResponseEntity<List> findAllLandingPage(RequestEntity<EventRequest>
	// requestEntity) {
	// EventRequest eventRequest = requestEntity.getBody();
	//
	//
	//// if (date != null) {
	//// if ("today".equalsIgnoreCase(date)) {
	//// eventRequest.setStartDate(LocalDate.now());
	//// eventRequest.setEndDate(LocalDate.now());
	//// } else if ("tomorrow".equalsIgnoreCase(date)) {
	//// eventRequest.setStartDate(LocalDate.now().plusDays(1));
	//// eventRequest.setEndDate(LocalDate.now().plusDays(1));
	//// } else if ("thisweek".equalsIgnoreCase(date)) {
	//// eventRequest.setStartDate(LocalDate.now());
	//// eventRequest.setEndDate(LocalDate.now().plusDays(7));
	//// }
	//// }
	//
	// List<EventLandingPage> landingPages =
	// eventService.findAllLandingPageByFilter(eventRequest);
	// if (landingPages == null || landingPages.isEmpty()) {
	// LOG.warn("::findAllLandingPage IsNULL request: {}",
	// gson.toJson(landingPages));
	// landingPages = Collections.emptyList();
	// }
	//
	// return new ResponseEntity<>(landingPages, HttpStatus.OK);
	// }

	@GetMapping(value = "/events/pages")
	public ResponseEntity<List> findAllPagesByFilter(@RequestParam(defaultValue = "RU") String language,
			@RequestParam(required = false, defaultValue = "1") int type,
			@RequestParam(required = false, defaultValue = "0") int limit,
			@RequestParam(required = false, defaultValue = "false") boolean random,
			@RequestParam(required = false, defaultValue = "false") boolean distinct,
			@RequestParam(required = false, defaultValue = "") String districts,
			@RequestParam(required = false, defaultValue = "") String regions,
			@RequestParam(required = false, defaultValue = "0") int status) {
		EventRequest eventRequest = new EventRequest();

		if (type > 1) {
			eventRequest.setType(EventType.getById(type));
		}
		if (limit > 0) {
			eventRequest.setLimit(limit);
		}
		if (random) {
			eventRequest.setRandom(Boolean.TRUE);
		}
		if (!districts.isBlank()) {
			eventRequest.setDistricts(districts.split(","));
		}
		if (!regions.isBlank()) {
			eventRequest.setRegions(regions.split(","));
		}

		eventRequest.setLanguage(Language.getByCode(language));
		
		eventRequest.setStatus(EventStatus.getById(status));

		List<EventLandingPage> pages = eventService.findAllLandingPageByFilter(eventRequest);

		

		return new ResponseEntity<>(pages, HttpStatus.OK);
	}

	@PostMapping(value = "/events/pages")
	public ResponseEntity<EventLandingPage> saveLandingPage(RequestEntity<EventLandingPage> requestEntity) {
		EventLandingPage page = requestEntity.getBody();
		eventService.saveLandingPage(page);

		return new ResponseEntity<>(page, HttpStatus.OK);

	}

	@GetMapping(value = "/events/{id}/files")
	public ResponseEntity<List> findPageByIdAndLanguage(@PathVariable long id) {
		List<MyFile> images = fileService.getFilesByPageId(LandingPageType.EVENT.getId(), id);
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	@GetMapping(value = "/events/files")
	public ResponseEntity<List> findFiles() {
		List<LandingPageFile> images = fileService.getFiles();
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	@PostMapping(value = "/events/{id}/main-image")
	public ResponseEntity<Boolean> setMainPage(@PathVariable long id, RequestEntity<Long> requestEntity) {
		long fileId = requestEntity.getBody();
		placeService.setMainImage(id, fileId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping(value = "/events/{id}/time-table")
	public ResponseEntity<List> getTimeTableByEventId(@PathVariable long id) {
		List<TimeTable> timetable = eventService.getTimeTableByEventId(id);
		return new ResponseEntity<>(timetable, HttpStatus.OK);
	}

	@PostMapping(value = "/events/time-table")
	public ResponseEntity<Integer> saveTimeTable(RequestEntity<TimeTable> requestEntity) {
		int result = eventService.saveTimeTable(requestEntity.getBody());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/events/time-table/{id}")
	public ResponseEntity<Integer> deleteTimeTableById(@PathVariable long id) {
		int result = eventService.deleteTimeTableById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
