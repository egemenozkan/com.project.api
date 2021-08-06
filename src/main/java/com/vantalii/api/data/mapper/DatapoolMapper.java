package com.vantalii.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.project.api.data.model.gis.City;
import com.project.api.data.model.gis.Country;
import com.project.api.data.model.gis.Region;
import com.project.api.data.model.gis.Subregion;
import com.project.common.model.Nationality;

@Mapper
public interface DatapoolMapper {
    @Select("SELECT id, name FROM datapool.nationality")
    List<Nationality> getNationalities();
    
    @Select("SELECT id, name, code, phone_code FROM datapool.country")
    List<Country> getCountries();

    @Select("SELECT id, name, country_id, phone_code, plate_code FROM datapool.city WHERE country_id = #{countryId}")
    List<City> getCitiesByCountryId(int countryId);
    
    @Select("SELECT id, name, code, city_id FROM datapool.region WHERE type = 2 AND city_id = #{cityId}")
    @Results(value = {
	    @Result(property = "city", column = "city_id", javaType = City.class, one = @One(select = "getCityById")) })
    List<Region> getRegionsByCityId(int cityId);
    
    @Select("SELECT id, name, country_id, phone_code, plate_code FROM datapool.city WHERE id = #{cityId}")
    City getCityById(int cityId);
    
    @Select("SELECT id, name, code, city_id, parent_id FROM datapool.region WHERE parent_id = #{regionId}")
    @Results(value = {
	    @Result(property = "region", column = "parent_id", javaType = Region.class, one = @One(select = "getRegionById")) })
    List<Subregion> getSubregionsByRegionId(int regionId);
    
    @Select("SELECT id, name, code, city_id, parent_id FROM datapool.region WHERE id = #{regionId}")
    @Results(value = {
	    @Result(property = "city", column = "city_id", javaType = City.class, one = @One(select = "getCityById")) })
    Region getRegionById(int regionId);
}
