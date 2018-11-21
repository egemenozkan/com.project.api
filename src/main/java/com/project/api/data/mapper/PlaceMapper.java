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

	@Select("SELECT pv.id, pv.name, pv.type FROM project.place_view_v2 pv WHERE pv.id = #{id} AND pv.closed 0")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class) })
	Place findPlaceById(long id);
	
	@Select("SELECT pv.id, pv.name, pv.type FROM project.place_view_v2 pv")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class) })
	List<Place> findAllPlace();
	
	@Insert("INSERT INTO project.place(type, address_id) VALUES(#{type.id}, #{address.id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createPlace(Place place);
	
	@Update("UPDATE project.place SET slug = #{slug} WHERE id= #{id}")
	void updatePlace(Place place);
	
	@Insert("INSERT INTO project.address(lat, lng, address_title, address_line_1, address_line_2, region_id, city_id, post_code, description) "
			+ "VALUES(#{lat}, #{lng}, #{addressTitle}, #{addressLine1}, #{addressLine2}, #{regionId}, #{cityId}, #{postCode}, #{description})")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createPlaceAddress(Address address);
	
	@Update("UPDATE project.address SET lat = #{lat}, lng = #{lng}, address_title = #{addressTitle}, address_line_1 = #{addressLine1}, address_line_2 = #{addressLine2}, "
			+ "region_id = #{regionId}, city_id = #{cityId}, post_code = #{postCode}, description = #{description} WHERE id = #{id}")
	void updatePlaceAddress(Address address);
	
	@Insert("INSERT INTO project.place(type) VALUES(#{type.id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createRestaurantCafe(RestaurantCafe restaurantCafe);
	
	@Update("UPDATE project.place SET")
	void updateRestaurantCafe(RestaurantCafe restaurantCafe);
	
	@Insert("INSERT INTO project.place_name(name, language, place_id) VALUES(#{name}, #{language}, #{placeId}) "
			+ "ON DUPLICATE KEY UPDATE  name = #{name}")
	void savePlaceName(String name, String language, long placeId);
	
	

}