package com.project.api.data.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.project.api.data.enums.LandingPageType;
import com.project.api.data.mapper.EventMapper;
import com.project.api.data.mapper.TimeTableMapper;
import com.project.api.data.model.event.Event;
import com.project.api.data.model.event.EventLandingPage;
import com.project.api.data.model.event.EventRequest;
import com.project.api.data.model.event.TimeTable;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.model.place.Place;
import com.project.api.data.service.IEventService;
import com.project.api.data.service.IPlaceService;
import com.project.api.utils.ApiUtils;
import com.project.common.enums.Language;

@Service
public class EventService implements IEventService {
	@Autowired
	private EventMapper eventMapper;
	
	@Autowired
	private TimeTableMapper timeTableMapper;

	@Autowired
	private IPlaceService placeService;

	@Override
	public Event getEventById(long id, Language language, long timeTableId) {
		Event event = eventMapper.findEventById(id, language.getCode(), timeTableId);
		
		/** although user change timeTableId, find event **/
		if (event == null && timeTableId > 0) {
			event = eventMapper.findEventById(id, language.getCode(), 0);
		}
		

		
		if (event != null) {
			if (event.getMaster() != null && event.getMaster().getId() > 0) {
				event.setMaster(eventMapper.findEventById(event.getMaster().getId(), language.getCode(), 0));
			}

			List<Localisation> names = eventMapper.findAllEventNameByEventId(event.getId());
			Map<String, Localisation> localisation = new HashMap<>();
			for (Localisation name : names) {
				if (name != null && name.getLanguage() != null)
					localisation.put(name.getLanguage().toString(), name);
			}
			event.setLocalisation(localisation);
		}
		
		return event;
	}

	@Override
	public List<Event> getEvents(EventRequest eventRequest) {
		List<Event> events = eventMapper.findAllEventsByFilter(eventRequest);

		if (events != null && !events.isEmpty()) {
			for (Event event : events) {
				List<Localisation> names = eventMapper.findAllEventNameByEventId(event.getId());
				Map<String, Localisation> localisation = new HashMap<>();
				for (Localisation name : names) {
					if (name != null && name.getLanguage() != null)
						localisation.put(name.getLanguage().toString(), name);
				}
				event.setLocalisation(localisation);
				
				/** Place **/
				if (!eventRequest.isHidePlace() && event != null && event.getPlace() != null && event.getPlace().getId() > 0) {
					Place place = placeService.findPlaceById(event.getPlace().getId(), eventRequest.getLanguage().getCode());
					event.setPlace(place);
				}
			}
		} else {
			events = Collections.emptyList();
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
					ApiUtils.generateSlug(event.getName(), event.getId()));
		}
		if (event != null && event.getLocalisation() != null) {
			event.getLocalisation().entrySet().stream()
					.filter(l -> (l.getValue() != null && l.getValue().getName() != null && !l.getValue().getName().isEmpty()))
					.forEach(e -> eventMapper.saveEventName(e.getValue().getName(), Language.valueOf(e.getKey()).getCode(), event.getId(),
							ApiUtils.generateSlug(e.getValue().getName(), event.getId())));
		}
		
		if (event != null && event.getBiletixId() != null) {
			TimeTable timeTable = new TimeTable();
			timeTable.setStartDate(event.getStartDate());
			timeTable.setStartTime(event.getStartTime());
			timeTable.setEndDate(event.getStartDate());
			timeTable.setEndTime(event.getStartTime().plusMinutes(event.getDuration()));
			timeTable.setPeriodType(event.getPeriodType());
			timeTable.setPageId(event.getId());
			timeTable.setPageType(LandingPageType.EVENT);
			saveTimeTable(timeTable);
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
		landingPage.setEvent(getEventById(eventRequest.getId(), eventRequest.getLanguage(), eventRequest.getTimeTableId()));
		landingPage.getEvent().setPlace(placeService.findPlaceById(landingPage.getEvent().getPlace().getId(),
				landingPage.getEvent().getLanguage().getCode()));
		return landingPage;
	}

	@Override
	public void saveLandingPage(EventLandingPage page) {
		if (page != null) {
			eventMapper.saveEventLandingPage(page);
			if (page.getContents() != null && !page.getContents().isEmpty()) {
				if (page.getId() != 0 && page.getContents().get(0).getId() == 0) {
					eventMapper.insertContents(page.getId(), page.getContents());
				} else if (page.getId() != 0 && page.getContents().get(0).getId() != 0) {
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
			if (eventRequest.getDistinct() != null && eventRequest.getDistinct() && !CollectionUtils.isEmpty(landingPages)) {
				Iterator<EventLandingPage> itr = landingPages.iterator();
				EventLandingPage previous = itr.next();
				while (itr.hasNext()) {
					EventLandingPage next = itr.next();

					if (previous.getId() == next.getId()) {
						itr.remove();
					}
					previous = next;
				}
			}
			
			
			for (EventLandingPage landingPage : landingPages) {
				if (landingPage != null) {
					EventRequest eventRequest2 = new EventRequest();
					eventRequest2.setId(landingPage.getId());
					eventRequest2.setLanguage(eventRequest.getLanguage());
					landingPage.setEvent(eventMapper.findAllEventsByFilter(eventRequest2).get(0));
					if (landingPage.getEvent() != null && landingPage.getEvent().getPlace() != null
							&& landingPage.getEvent().getPlace().getId() > 0) {
						landingPage.getEvent()
								.setPlace(placeService.findPlaceById(landingPage.getEvent().getPlace().getId(),
										landingPage.getEvent().getLanguage().toString()));
					}
				}
			}
		}

		return landingPages;
	}

	@Override
	public List<TimeTable> getTimeTableByEventId(long eventId) {
		return timeTableMapper.findAllTimeTableByEventId(eventId);
	}

	@Override
	public int saveTimeTable(TimeTable timeTable) {
		return timeTableMapper.saveTimeTable(timeTable);
	}

	@Override
	public int deleteTimeTableById(long id) {
		return timeTableMapper.deleteTimeTableById(id);
	}

	@Override
	public Event findByBiletixId(String biletixId) {
		return eventMapper.findEventByBiletixKey(biletixId);
	}

}
