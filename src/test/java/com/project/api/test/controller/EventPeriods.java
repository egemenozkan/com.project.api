package com.project.api.test.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.project.api.data.enums.PeriodType;

public class EventPeriods {
	@Autowired
	private Gson gson;

	@Test
	public void test() {
		PeriodType periodType = PeriodType.MONDAYS;
		LocalDate startDate = LocalDate.of(2019, 02, 17);
		LocalDate endDate = LocalDate.of(2019, 03, 25);
		Stream<LocalDate> sessions = startDate.datesUntil(endDate)
				.filter(date -> isEventBetweenPeriods(date, periodType));
		List<LocalDate> sessions2 = sessions.collect(Collectors.toList());
		System.out.println(gson.toJson(sessions.collect(Collectors.toList())));
	}

	private boolean isEventBetweenPeriods(LocalDate date, PeriodType periodType) {
		boolean result = false;
		switch (periodType) {
		case MONDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.MONDAY) == 0;
			break;
		case TUESDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.TUESDAY) == 0;
			break;
		case WEDNESDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.WEDNESDAY) == 0;
			break;
		case THURSDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.THURSDAY) == 0;
			break;
		case FRIDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.FRIDAY) == 0;
			break;
		case SATURDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0;
			break;
		case SUNDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0;
			break;
		case WEEKDAYS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) != 0 || date.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) != 0;
			break;
		case WEEKENDS:
			result = date.getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0 || date.getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0;
			break;
		default:
			break;
		}
		
		return result;
		
	}
	
}
