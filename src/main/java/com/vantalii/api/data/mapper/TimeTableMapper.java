package com.vantalii.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.api.data.model.event.TimeTable;

@Mapper
public interface TimeTableMapper {
	List<TimeTable> findAllTimeTableByEventId(long eventId);
	
	List<TimeTable> findAllTimeTableByPlaceId(long placeId);

	int saveTimeTable(TimeTable timeTable);

	int deleteTimeTableById(long id);
}
