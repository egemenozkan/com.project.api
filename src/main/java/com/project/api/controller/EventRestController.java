package com.project.api.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.api.data.enums.Language;
import com.project.api.data.model.event.Event;
import com.project.api.data.model.event.EventLandingPage;
import com.project.api.data.model.event.EventRequest;
import com.project.api.data.service.IEventService;

@RestController
@RequestMapping(value = "/api/v1/")
public class EventRestController {

	@Autowired
	private Gson gson;

	@Autowired
	private IEventService eventService;

	private static final Logger LOG = LogManager.getLogger(EventRestController.class);

	@GetMapping(value = "/events")
	public ResponseEntity<List<Event>> getEvents(RequestEntity<EventRequest> requestEntity) {
		EventRequest eventRequest = new EventRequest();
		eventRequest.setLanguage(Language.TURKISH);
		List<Event> events = eventService.getEvents(eventRequest);

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	@GetMapping(value = "/events/{id}")
	public ResponseEntity<Event> getEventById(@PathVariable long id, @RequestParam(defaultValue = "RU", required = false) String language) {
		Event event = eventService.getEventById(id, Language.getByCode(language));
		if (LOG.isDebugEnabled()) {
			LOG.debug("::getEventById {}", gson.toJson(event));
		}
		return new ResponseEntity<>(event, HttpStatus.OK);
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
			@RequestParam(defaultValue = "RU", required = false) String language, @RequestParam(required = false) String date) {
		EventRequest eventRequest = new EventRequest();
		eventRequest.setId(id);
		eventRequest.setLanguage(Language.getByCode(language));

		EventLandingPage landingPage = eventService.findLandingPageByEventId(eventRequest);
		if (landingPage == null) {
			LOG.warn("::findLandingPageByEventId IsNULL eventId: {}, language: {}", id, language);
		}

		return new ResponseEntity<>(landingPage, HttpStatus.OK);
	}
	
	@GetMapping(value = "/events/pages")
	public ResponseEntity<List> findAllLandingPage(RequestEntity<EventRequest> requestEntity) {
		EventRequest eventRequest = requestEntity.getBody();


//		if (date != null) {
//			if ("today".equalsIgnoreCase(date)) {
//				eventRequest.setStartDate(LocalDate.now());
//				eventRequest.setEndDate(LocalDate.now());
//			} else if ("tomorrow".equalsIgnoreCase(date)) {
//				eventRequest.setStartDate(LocalDate.now().plusDays(1));
//				eventRequest.setEndDate(LocalDate.now().plusDays(1));
//			} else if ("thisweek".equalsIgnoreCase(date)) {
//				eventRequest.setStartDate(LocalDate.now());
//				eventRequest.setEndDate(LocalDate.now().plusDays(7));
//			}
//		}

		List<EventLandingPage> landingPages = eventService.findAllLandingPageByFilter(eventRequest);
		if (landingPages == null || landingPages.isEmpty()) {
			LOG.warn("::findAllLandingPage IsNULL request: {}", gson.toJson(landingPages));
			landingPages = Collections.emptyList();
		}

		return new ResponseEntity<>(landingPages, HttpStatus.OK);
	}

	@PostMapping(value = "/events/pages")
	public ResponseEntity<EventLandingPage> saveLandingPage(RequestEntity<EventLandingPage> requestEntity) {
		EventLandingPage page = requestEntity.getBody();
		eventService.saveLandingPage(page);

		return new ResponseEntity<>(page, HttpStatus.OK);

	}

}
