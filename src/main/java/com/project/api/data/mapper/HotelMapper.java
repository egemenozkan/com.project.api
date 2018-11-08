package com.project.api.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.hotel.Hotel;

@Mapper
public interface HotelMapper {
    @Insert("INSERT INTO project.hotel(name, place_id) VALUES(#{name}, #{id})")
    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createHotel(Hotel hotel);
    
    @Update("UPDATE project.hotel SET name = #{name}, former_name = #{formerName} WHERE place_id = #{id}")
    void updateHotel(Hotel hotel);
}
