package com.project.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.common.Content;
import com.project.api.data.model.event.Event;
import com.project.api.data.model.event.EventLandingPage;
import com.project.api.data.model.event.EventRequest;
import com.project.api.data.model.place.Localisation;

@Mapper
public interface EventMapper extends PlaceMapper{
	
	final String SELECT_EVENT = "SELECT ev.id, ev.place_id,  ev.id AS event_id, ev.type, ev.period_type, #{language} AS language," +
			"ev.start_date, ev.start_time, ev.end_date, ev.end_time, ev.show_start_time, ev.show_end_time," +
			"CASE #{language}" + 
			" WHEN 'TR' THEN ev.tr_name WHEN 'EN' THEN ev.en_name WHEN 'RU' THEN ev.ru_name WHEN 'DE' THEN ev.de_name END AS name," + 
			" CASE #{language}" + 
			" WHEN 'TR' THEN ev.tr_slug WHEN 'EN' THEN ev.en_slug WHEN 'RU' THEN ev.ru_slug WHEN 'DE' THEN ev.de_slug END AS slug" + 
			" ,ev.create_datetime, ev.update_datetime" +
			" FROM project.event_view ev";	
//	@Select(SELECT_EVENT)
//	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.EventType.class, typeHandler = com.project.api.data.mapper.handler.EventTypeTypeHandler.class),
//			@Result(property = "periodType", column = "period_type", javaType = com.project.api.data.enums.EventPeriodType.class, typeHandler = com.project.api.data.mapper.handler.EventPeriodTypeTypeHandler.class),
//			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class),
//			@Result(property = "place.id", column = "place_id")})
//	List<Event> findAllEvent(EventRequest eventRequest);
	
	@Select(SELECT_EVENT + " WHERE ev.id = #{id}")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.EventType.class, typeHandler = com.project.api.data.mapper.handler.EventTypeTypeHandler.class),
			@Result(property = "periodType", column = "period_type", javaType = com.project.api.data.enums.EventPeriodType.class, typeHandler = com.project.api.data.mapper.handler.EventPeriodTypeTypeHandler.class),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class),
			@Result(property = "place.id", column = "place_id")})
	Event findEventById(long id, String language);
	
	@Insert("INSERT INTO project.event(type, period_type, start_date, start_time, show_start_time, end_date, end_time, show_end_time, place_id)"
			+ " VALUES(#{type.id}, #{periodType.id}, #{startDate}, #{startTime}, #{showStartTime}"
			+ ", #{endDate} , #{endTime}, #{showEndTime}, #{place.id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createEvent(Event event);
	
	@Update("UPDATE project.event SET update_count = update_count + 1, place_id = #{place.id}, type = #{type.id}, period_type = #{periodType.id},"
			+ " start_date = #{startDate}, start_time = #{startTime}, show_start_time = #{showStartTime} ,"
			+ " end_date = #{endDate}, end_time = #{endTime}, show_end_time = #{showEndTime}"
			+ " WHERE id= #{id}")
	void updateEvent(Event event);
	
	@Insert("INSERT INTO project.event_name(name, language, event_id, slug) VALUES(#{name}, #{language}, #{placeId}, #{slug}) "
			+ "ON DUPLICATE KEY UPDATE  name = #{name}, slug = #{slug}")
	void saveEventName(String name, String language, long placeId, String slug);
	
	@Select("SELECT en.name, en.language, en.slug FROM project.event_name en WHERE en.event_id = #{id}")
	@Results(value = {@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Localisation> findAllEventNameByEventId(long id);
		
	List<EventLandingPage> findAllLandingPageByFilter(EventRequest eventRequest);
	
	@Select("SELECT c.id, c.title, c.text, c.order FROM project.content c WHERE c.page_id = #{id}")
	List<Content> findAllContentByPageId(long id);
	
	@Insert("INSERT INTO project.landing_page(title, description, keywords, language, self_id, type) VALUES(#{title}, #{description}, #{keywords}, #{language.code}, #{event.id}, 2) "
			+ "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id), title = #{title}, description = #{description}, keywords = #{keywords}")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void saveEventLandingPage(EventLandingPage page);
	
	void insertContents(@Param("id") long id, @Param("contents") List<Content> contents);
	
	void updateContents(@Param("contents") List<Content> contents);
	
	List<Event> findAllEventsByFilter(EventRequest eventRequest);
	
	
}
