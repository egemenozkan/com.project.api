package com.project.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.common.Address;
import com.project.api.data.model.common.MyPlace;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.RestaurantCafe;

@Mapper
public interface PlaceMapper {

	@Insert("INSERT INTO datapool.place(name, longitude, latitude, fb_place_id) VALUES(#{name}, #{coordinate.x}, #{coordinate.y}, #{fbPlaceId}) "
			+ "ON DUPLICATE KEY UPDATE name = #{name}, longitude = #{coordinate.x}, latitude = #{coordinate.y}")
	void saveFacebookPlace(MyPlace place);

	@Select("SELECT pv.id, pv.name, pv.type FROM project.place_view pv WHERE pv.id = #{id} AND pv.closed 0")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class) })
	Place findPlaceById(long id);
	
	@Select("SELECT pv.id, pv.name, pv.type FROM project.place_view pv")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class) })
	List<Place> findAllPlace();
	
	@Insert("INSERT INTO project.place(type) VALUES(#{type.id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createPlace(Place place);
	
	@Update("UPDATE project.place SET")
	void updatePlace(Place place);
	
	@Insert("INSERT INTO project.address(lat, lng) VALUES(#{lat}, #{coordinate.x}, #{coordinate.y}, #{fbPlaceId}) "
			+ "ON DUPLICATE KEY UPDATE lat = #{lat}, lng = #{lng}")
	void createPlaceAddress(Address address);
	
	@Update("INSERT INTO project.address(lat, lng) VALUES(#{lat}, #{coordinate.x}, #{coordinate.y}, #{fbPlaceId})")
	void updatePlaceAddress(Address address);
	
	@Insert("INSERT INTO project.place(type) VALUES(#{type.id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createRestaurantCafe(RestaurantCafe restaurantCafe);
	
	@Update("UPDATE project.place SET")
	void updateRestaurantCafe(RestaurantCafe restaurantCafe);
	
	

}