package com.project.api.data.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.enums.Language;
import com.project.api.data.mapper.EventMapper;
import com.project.api.data.model.event.Event;
import com.project.api.data.model.event.EventLandingPage;
import com.project.api.data.model.event.EventRequest;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.service.IEventService;
import com.project.api.data.service.IPlaceService;
import com.project.api.utils.WebUtils;

@Service
public class EventService implements IEventService {
	@Autowired
	private EventMapper eventMapper;

	@Autowired
	private IPlaceService placeService;

	@Override
	public Event getEventById(long id, Language language) {
		Event event = eventMapper.findEventById(id, language.getCode());

		List<Localisation> names = eventMapper.findAllEventNameByEventId(event.getId());
		Map<String, Localisation> localisation = new HashMap<>();
		for (Localisation name : names) {
			if (name != null && name.getLanguage() != null)
				localisation.put(name.getLanguage().toString(), name);
		}
		event.setLocalisation(localisation);

		return event;

	}

	@Override
	public List<Event> getEvents(EventRequest eventRequest) {
		List<Event> events = eventMapper.findAllEventsByFilter(eventRequest);
		if (events != null && !events.isEmpty()) {
			// events.removeIf(event -> event.getType() != eventRequest.g)
		}
		if (events != null && !events.isEmpty()) {
			for (Event event : events) {
				List<Localisation> names = eventMapper.findAllEventNameByEventId(event.getId());
				Map<String, Localisation> localisation = new HashMap<>();
				for (Localisation name : names) {
					if (name != null && name.getLanguage() != null)
						localisation.put(name.getLanguage().toString(), name);
				}
				event.setLocalisation(localisation);

			}
		}

		return events;
	}

	@Override
	public Event saveEvent(Event event) {
		if (event != null && event.getId() == 0) {
			eventMapper.createEvent(event);
		} else if (event != null && event.getId() != 0) {
			eventMapper.updateEvent(event);
		}

		/** PlaceName/Slug SAVE--UPDATE **/
		if (event != null && event.getLanguage() != null) {
			eventMapper.saveEventName(event.getName(), event.getLanguage().getCode(), event.getId(),
					WebUtils.generateSlug(event.getName(), event.getId()));
		}
		if (event != null && event.getLocalisation() != null) {
			event.getLocalisation().entrySet().stream()
					.filter(l -> (l.getValue() != null && l.getValue().getName() != null && !l.getValue().getName().isEmpty()))
					.forEach(e -> eventMapper.saveEventName(e.getValue().getName(), Language.valueOf(e.getKey()).getCode(), event.getId(),
							WebUtils.generateSlug(e.getValue().getName(), event.getId())));
			// eventMapper.savePlaceName(e.getValue().getName(),
			// e.getValue().getLanguage().getCode(), place.getId())

		}
		return null;
	}

	@Override
	public EventLandingPage findLandingPageByEventId(EventRequest eventRequest) {
		List<EventLandingPage> landingPages = eventMapper.findAllLandingPageByFilter(eventRequest);

		if (landingPages == null || landingPages.isEmpty()) {
			return null;
		}
		EventLandingPage landingPage = landingPages.get(0);
		landingPage.setEvent(getEventById(eventRequest.getId(), eventRequest.getLanguage()));
		landingPage.getEvent().setPlace(placeService.findPlaceById(landingPage.getEvent().getPlace().getId(),
				landingPage.getEvent().getLanguage().toString()));
		return landingPage;
	}

	@Override
	public void saveLandingPage(EventLandingPage page) {
		if (page != null) {
			long id = page.getId();
			eventMapper.saveEventLandingPage(page);
			if (page.getContents() != null && !page.getContents().isEmpty()) {
				if (id != 0 && page.getContents().get(0).getId() == 0) {
					eventMapper.insertContents(page.getId(), page.getContents());
				} else if (id != 0 && page.getContents().get(0).getId() != 0) {
					eventMapper.updateContents(page.getContents());
				}
			}
		}
	}

	@Override
	public List<EventLandingPage> findAllLandingPageByFilter(EventRequest eventRequest) {
		List<EventLandingPage> landingPages = eventMapper.findAllLandingPageByFilter(eventRequest);

		if (landingPages == null || landingPages.isEmpty()) {
			landingPages = Collections.emptyList();
		} else {
			for (EventLandingPage landingPage : landingPages) {
				if (landingPage != null && landingPage.getEvent() != null && landingPage.getEvent().getPlace() != null
						&& landingPage.getEvent().getPlace().getId() > 0) {
					landingPage.getEvent().setPlace(placeService.findPlaceById(landingPage.getEvent().getPlace().getId(),
							landingPage.getEvent().getLanguage().toString()));
				}
			}
		}

		return landingPages;
	}

}
