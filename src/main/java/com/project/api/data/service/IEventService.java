package com.project.api.data.service;

import java.util.List;

import com.project.api.data.enums.Language;
import com.project.api.data.model.event.Event;
import com.project.api.data.model.event.EventLandingPage;
import com.project.api.data.model.event.EventRequest;
import com.project.api.data.model.event.TimeTable;

public interface IEventService {
	public Event getEventById(long id, Language language, long timeTableId);

	public List<Event> getEvents(EventRequest eventRequest);

	public Event saveEvent(Event event);

	public EventLandingPage findLandingPageByEventId(EventRequest eventRequest);

	public List<EventLandingPage> findAllLandingPageByFilter(EventRequest eventRequest);

	public void saveLandingPage(EventLandingPage page);

	public List<TimeTable> getTimeTableByEventId(long eventId);

	public int saveTimeTable(TimeTable timeTable);
	
	public int deleteTimeTableById(long id);
	
	public Event findByBiletixId(String biletixId);

}
