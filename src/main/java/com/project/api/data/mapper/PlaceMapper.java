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
import com.project.api.data.model.common.MyPlace;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.RestaurantCafe;
import com.project.common.model.City;

@Mapper
public interface PlaceMapper {

	final String SELECT_PLACE = "SELECT pv.id, pv.type, pv.slug, #{language} AS language, pv.address_id," + 
			"CASE #{language}" + 
			" WHEN 'TR' THEN pv.tr_name WHEN 'EN' THEN pv.en_name WHEN 'RU' THEN pv.ru_name WHEN 'DE' THEN pv.de_name END AS name," + 
			"#{originalLanguage} AS original_language," +
			"CASE #{originalLanguage}" + 
			" WHEN 'TR' THEN pv.tr_name WHEN 'EN' THEN pv.en_name WHEN 'RU' THEN pv.ru_name WHEN 'DE' THEN pv.de_name END AS original_name " + 
			"FROM project.place_view_v2 pv";
	
	
	
	@Insert("INSERT INTO datapool.place(name, longitude, latitude, fb_place_id) VALUES(#{name}, #{coordinate.x}, #{coordinate.y}, #{fbPlaceId}) "
			+ "ON DUPLICATE KEY UPDATE name = #{name}, longitude = #{coordinate.x}, latitude = #{coordinate.y}")
	void saveFacebookPlace(MyPlace place);

	@Select(SELECT_PLACE + " WHERE pv.id = #{id}")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class),
			@Result(property = "originalLanguage", column = "original_language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	Place findPlaceById(long id, String language, String originalLanguage);
	
	@Select(SELECT_PLACE)
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class),
			@Result(property = "originalLanguage", column = "original_language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Place> findAllPlace(String language, String originalLanguage);
	
	@Insert("INSERT INTO project.place(type, address_id) VALUES(#{type.id}, #{address.id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createPlace(Place place);
	
	@Update("UPDATE project.place SET slug = #{slug} WHERE id= #{id}")
	void updatePlace(Place place);
	
	@Select("SELECT id, address_title, address, post_code, lat, lng, region_id, city_id FROM project.address WHERE id = #{id}")
	Address findAddressById(long id);
	
	@Insert("INSERT INTO project.address(lat, lng, address_title, address, region_id, city_id, post_code, description) "
			+ "VALUES(#{lat}, #{lng}, #{addressTitle}, #{address}, #{regionId}, #{cityId}, #{postCode}, #{description})")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createPlaceAddress(Address address);
	
	@Update("UPDATE project.address SET lat = #{lat}, lng = #{lng}, address_title = #{addressTitle}, address = #{address}, "
			+ "region_id = #{regionId}, city_id = #{cityId}, post_code = #{postCode}, description = #{description} WHERE id = #{id}")
	void updatePlaceAddress(Address address);
	
	@Insert("INSERT INTO project.restaurant_cafe(place_id) VALUES(#{id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createRestaurantCafe(RestaurantCafe restaurantCafe);
	
	@Update("UPDATE project.restaurant_cafe SET place_id = place_id WHERE place_id = #{id}")
	void updateRestaurantCafe(RestaurantCafe restaurantCafe);
	
	@Insert("INSERT INTO project.place_name(name, language, place_id) VALUES(#{name}, #{language}, #{placeId}) "
			+ "ON DUPLICATE KEY UPDATE  name = #{name}")
	void savePlaceName(String name, String language, long placeId);
	
	

}