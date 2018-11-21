package com.project.api.data.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.flight.Airport;

@Mapper
public interface AirportMapper {
    @Insert("INSERT INTO project.airport(place_id) VALUES(#{id})")
    @SelectKey(statement = "select last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createAirport(Airport airport);
    
    @Update("UPDATE project.airport SET place_id = #{id} WHERE place_id = #{id}")
    void updateAirport(Airport airport);
}
