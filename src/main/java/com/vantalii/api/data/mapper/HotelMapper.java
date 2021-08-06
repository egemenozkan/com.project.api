package com.vantalii.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.hotel.Hotel;

@Mapper
public interface HotelMapper {
    @Insert("INSERT INTO project.hotel(star, place_id) VALUES(#{star.id}, #{id})")
    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createHotel(Hotel hotel);
    
    @Update("UPDATE project.hotel SET star = #{star.id} WHERE place_id = #{id}")
    void updateHotel(Hotel hotel);
    
    @Select("SELECT h.region_id, h.name, h.category_id AS star, hc.lat, hc.lng FROM datapool.hotels h LEFT JOIN datapool.hotel_coordinates hc ON h.id = hc.hotel_id WHERE h.closed = 0")
    List<Hotel> findAllHotel();
    
    @Select("SELECT h.region_id AS address.region_id, h.name, h.category_id AS star, hc.lat AS address.lat, hc.lng AS address.lng FROM datapool.hotels h LEFT JOIN datapool.hotel_coordinates hc ON h.id = hc.hotel_id WHERE h.closed = 0")
    Hotel findHotelById(long id);
}
