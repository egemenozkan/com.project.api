package com.project.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.common.Address;
import com.project.api.data.model.common.Contact;
import com.project.api.data.model.common.Content;
import com.project.api.data.model.common.MyPlace;
import com.project.api.data.model.file.MyFile;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.model.place.Place;
import com.project.api.data.model.place.PlaceLandingPage;
import com.project.api.data.model.place.PlaceRequest;
import com.project.api.data.model.place.RestaurantCafe;

@Mapper
public interface PlaceMapper {

	final String SELECT_PLACE = "SELECT pv.id, pv.id AS localisation_id, pv.main_image_id, pv.id AS place_id, pv.type, IF(#{language} IS NULL or #{language} = '', 'TR', #{language}) AS language, pv.address_id," + 
			"CASE IF(#{language} IS NULL or #{language} = '', 'TR', #{language})" + 
			" WHEN 'TR' THEN pv.tr_name WHEN 'EN' THEN pv.en_name WHEN 'RU' THEN pv.ru_name WHEN 'DE' THEN pv.de_name END AS name," + 
			" CASE IF(#{language} IS NULL or #{language} = '', 'TR', #{language})" + 
			" WHEN 'TR' THEN pv.tr_slug WHEN 'EN' THEN pv.en_slug WHEN 'RU' THEN pv.ru_slug WHEN 'DE' THEN pv.de_slug END AS slug" + 
			" ,pv.create_datetime, pv.update_datetime, pv.opening_time, pv.closing_time, pv.twenty_four_seven" +
			" FROM project.place_view_v2 pv";
		
	@Insert("INSERT INTO datapool.place(name, longitude, latitude, fb_place_id) VALUES(#{name}, #{coordinate.x}, #{coordinate.y}, #{fbPlaceId}) "
			+ "ON DUPLICATE KEY UPDATE name = #{name}, longitude = #{coordinate.x}, latitude = #{coordinate.y}")
	void saveFacebookPlace(MyPlace place);

	@Select(SELECT_PLACE + " WHERE pv.id = #{id}")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address.id", column = "address_id"),
//			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
//			@Result(property = "contact", column = "place_id", javaType = Contact.class, one = @One(select = "findContactByPlaceId")),
//			@Result(property="images", column="place_id", javaType=List.class, many=@Many(select="findAllImagesByPlaceId")),
			@Result(property="mainImage.id", column="main_image_id"),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
//	@Options(flushCache = Options.FlushCachePolicy.TRUE)
	Place findPlaceById(long id, String language);
	
	@Select(SELECT_PLACE + " ORDER BY pv.update_datetime DESC LIMIT 30")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
			@Result(property = "contact", column = "place_id", javaType = Contact.class, one = @One(select = "findContactByPlaceId")),
			@Result(property="images", column="place_id", javaType=List.class, many=@Many(select="findAllImagesByPlaceId")),
			@Result(property="mainImage", column="main_image_id", javaType=MyFile.class, one = @One(select="findMainImage")),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Place> findAllPlace(String language);
	
	@Select(SELECT_PLACE + " WHERE pv.type = #{typeId}")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
			@Result(property = "contact", column = "place_id", javaType = Contact.class, one = @One(select = "findContactByPlaceId")),
			@Result(property="images", column="place_id", javaType=List.class, many=@Many(select="findAllImagesByPlaceId")),
			@Result(property="mainImage", column="main_image_id", javaType=MyFile.class, one = @One(select="findMainImage")),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Place> findAllPlaceByType(String language, int typeId);
	
	@Select(SELECT_PLACE + " WHERE type ${types}")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
			@Result(property = "contact", column = "place_id", javaType = Contact.class, one = @One(select = "findContactByPlaceId")),
			@Result(property="images", column="place_id", javaType=List.class, many=@Many(select="findAllImagesByPlaceId")),
			@Result(property="mainImage", column="main_image_id", javaType=MyFile.class, one = @One(select="findMainImage")),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Place> findAllPlaceByMainType(String language, String types);
	
	@Insert("INSERT INTO project.place(type, address_id) VALUES(#{type.id}, #{address.id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createPlace(Place place);
	
	@Update("UPDATE project.place SET update_count = update_count + 1, type = #{type.id} WHERE id= #{id}")
	void updatePlace(Place place);
	
	@Select("SELECT av.id, av.address_title, av.address, av.post_code, av.lat, av.lng, av.subregion_id, av.subregion, av.region_id, av.region, av.city_id, av.city FROM project.address_view av WHERE av.id = #{id}")
	Address findAddressById(long id);
	
	@Insert("INSERT INTO project.address(lat, lng, address_title, address, region_id, city_id, post_code, description) "
			+ "VALUES(#{lat}, #{lng}, #{addressTitle}, #{address}, #{regionId}, #{cityId}, #{postCode}, #{description})")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createPlaceAddress(Address address);
	
	@Update("UPDATE project.address SET lat = #{lat}, lng = #{lng}, address_title = #{addressTitle}, address = #{address}, "
			+ "region_id = #{regionId}, city_id = #{cityId}, post_code = #{postCode}, description = #{description} WHERE id = #{id}")
	void updatePlaceAddress(Address address);
	
	@Insert("INSERT INTO project.place_contact (place_id, phone, whatsapp, call_center, web, email) "
			+ "VALUES(${placeId}, #{contact.phone}, #{contact.whatsapp}, #{contact.callCenter}, #{contact.web}, #{contact.email})"
			+ "ON DUPLICATE KEY UPDATE phone = #{contact.phone}, whatsapp = #{contact.whatsapp}, call_center = #{contact.callCenter}, web = #{contact.web}, email = #{contact.email}")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void savePlaceContact(Contact contact, long placeId);
	
	@Select("SELECT id, phone, whatsapp, call_center, web, email FROM project.place_contact WHERE place_id = #{placeId} AND closed = 0")
	Contact findContactByPlaceId(long placeId);
	
	@Insert("INSERT INTO project.restaurant_cafe(place_id) VALUES(#{id}) ")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void createRestaurantCafe(RestaurantCafe restaurantCafe);
	
	@Update("UPDATE project.restaurant_cafe SET place_id = place_id WHERE place_id = #{id}")
	void updateRestaurantCafe(RestaurantCafe restaurantCafe);
	
	@Insert("INSERT INTO project.place_name(name, language, place_id, slug) VALUES(#{name}, #{language}, #{placeId}, #{slug}) "
			+ "ON DUPLICATE KEY UPDATE  name = #{name}, slug = #{slug}")
	void savePlaceName(String name, String language, long placeId, String slug);
	
//	@Select("SELECT lp.id, lp.title, lp.description, lp.keywords, lp.language  FROM project.landing_page lp WHERE lp.self_id = #{id} AND lp.language = #{language} AND lp.type = 1")
//	@Results(value = {@Result(property = "id", column = "id", javaType = Long.class),
//			@Result(property = "contents", column = "id", javaType = List.class, one = @One(select = "findAllContentByPageId")),
//			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<PlaceLandingPage> findAllLandingPageByFilter(PlaceRequest placeRequest, @Param("types") List<Integer> typesByMainType);
	
	@Select("SELECT c.id, c.title, c.text, c.order FROM project.content c WHERE c.page_id = #{id}")
	List<Content> findAllContentByPageId(long id);
	
	@Insert("INSERT INTO project.landing_page(title, description, keywords, language, self_id, type) VALUES(#{title}, #{description}, #{keywords}, #{language.code}, #{place.id}, 1) "
			+ "ON DUPLICATE KEY UPDATE id=LAST_INSERT_ID(id), title = #{title}, description = #{description}, keywords = #{keywords}")
    @SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void saveLandingPage(PlaceLandingPage page);
	
	void insertContents(@Param("id") long id, @Param("contents") List<Content> contents);
	
	void updateContents(@Param("contents") List<Content> contents);
	
	@Select("SELECT pn.name, pn.language, pn.slug FROM project.place_name pn WHERE pn.place_id = #{id}")
	@Results(value = {@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Localisation> findAllPlaceNameByPlaceId(long id);
	
	@Select(SELECT_PLACE + " WHERE pv.tr_name LIKE '%${name}%' OR pv.ru_name LIKE '%${name}%' OR pv.en_name LIKE '%${name}%'")
	@Results(value = {@Result(property = "type", column = "type", javaType = com.project.api.data.enums.PlaceType.class, typeHandler = com.project.api.data.mapper.handler.PlaceTypeTypeHandler.class),
			@Result(property = "address", column = "address_id", javaType = Address.class, one = @One(select = "findAddressById")),
			@Result(property = "contact", column = "place_id", javaType = Contact.class, one = @One(select = "findContactByPlaceId")),
			@Result(property = "language", column = "language", javaType = com.project.api.data.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Place> autocomplete(String name, String language);
	
	@Update("UPDATE project.place SET main_image_id = #{fileId} WHERE id = #{id} ")
	void setMainImage(long id, long fileId);
	
	@Select("SELECT fs.id, fs.upload_dir, fs.name, fs.extension, fs.page_id, fs.page_type, fs.create_datetime, fs.update_datetime, fs.user_id, fs.status FROM project.file_storage fs WHERE page_id = #{id} AND page_type = 1 ORDER BY fs.create_datetime DESC")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "user.id", column = "user_id")})
	List<MyFile> findAllImagesByPlaceId(long id);
	
	@Select("SELECT fs.id, fs.upload_dir, fs.name, fs.extension, fs.page_id, fs.page_type, fs.create_datetime, fs.update_datetime, fs.user_id, fs.status FROM project.file_storage fs WHERE id = #{fileId}")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "user.id", column = "user_id")})
	MyFile findMainImage(long fileId);
	
}