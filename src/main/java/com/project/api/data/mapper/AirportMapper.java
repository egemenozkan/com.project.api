package com.project.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.common.Address;
import com.project.api.data.model.flight.Airport;
import com.project.api.data.model.place.Localisation;

@Mapper
public interface AirportMapper {
    @Insert("INSERT INTO project.airport(place_id) VALUES(#{id})")
    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createAirport(Airport airport);
    
    @Update("UPDATE project.airport SET place_id = #{id} WHERE place_id = #{id}")
    void updateAirport(Airport airport);
    
    @Select("SELECT av.id, av.id AS localisation_id, av.type, #{language} AS language, av.address_id," + 
    		"av.iata, av.dhm_id, av.icao," +
			"CASE #{language}" + 
			" WHEN 'TR' THEN av.tr_name WHEN 'EN' THEN av.en_name WHEN 'RU' THEN av.ru_name WHEN 'DE' THEN av.de_name END AS name," + 
			" CASE #{language}" + 
			" WHEN 'TR' THEN av.tr_slug WHEN 'EN' THEN av.en_slug WHEN 'RU' THEN av.ru_slug WHEN 'DE' THEN av.de_slug END AS slug" + 
			" ,av.create_datetime, av.update_datetime" +
			" FROM project.airport_view av")
    @Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
    List<Airport> findAllAirports();
    
    @Select("SELECT pn.name, pn.language, pn.slug FROM project.place_name pn WHERE pn.place_id = #{id}")
	@Results(value = {@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Localisation> findAllPlaceNameByPlaceId(long id);
    
	@Select("SELECT av.id, av.address_title, av.address, av.post_code, av.lat, av.lng, av.subregion_id, av.subregion, av.region_id, av.region, av.city_id, av.city FROM project.address_view av WHERE av.id = #{id}")
	Address findAddressById(long id);
}
