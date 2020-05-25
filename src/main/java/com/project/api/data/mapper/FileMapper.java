package com.project.api.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.project.api.data.model.comment.Comment;
import com.project.api.data.model.comment.PlaceComment;
import com.project.api.data.model.file.LandingPageFile;
import com.project.api.data.model.file.MyFile;

@Mapper
public interface FileMapper {
	@Select("SELECT c.id, c.title, c.message, c.rating, c.status, c.user_id, c.username, c.first_name, c.last_name, c.email, c.place_id, c.name, c.language, c.create_datetime, c.update_datetime"
			+ " FROM project.place_comment_view c WHERE c.place_id = #{id}")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "user.id", column = "user_id"),
			@Result(property = "user.name", column = "username"),
			@Result(property = "user.firstName", column = "first_name"),
			@Result(property = "user.lastName", column = "last_name"),
			@Result(property = "user.email", column = "email"),
			@Result(property = "language", column = "language", javaType = com.project.common.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Comment> findAllCommentsByPlaceId(long id, String language);
	
	@Insert("INSERT INTO project.file_storage(page_type, page_id, user_id, upload_dir, name, extension) VALUES(#{pageType}, #{pageId}, #{userId}, #{uploadDir}, #{name}, #{extension})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	long saveFile(int pageType, long pageId, long userId, String uploadDir, String name, String extension);
	
	@Update("UPDATE project.place_comment SET status = ${status} WHERE id = ${id}")
	long updateCommentStatus(long id, int status);
	
	
	@Select("SELECT c.id, c.title, c.message, c.rating, c.status, c.user_id, c.username, c.first_name, c.last_name, c.email, c.place_id, c.name, c.language, c.create_datetime, c.update_datetime"
			+ " FROM project.place_comment_view c")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "place.id", column = "place_id"),
			@Result(property = "place.name", column = "name"),
			@Result(property = "user.id", column = "user_id"),
			@Result(property = "user.name", column = "username"),
//			@Result(property = "user.firstName", column = "first_name"),
//			@Result(property = "user.lastName", column = "last_name"),
			@Result(property = "user.email", column = "email"),
			@Result(property = "language", column = "language", javaType = com.project.common.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<PlaceComment> findAllPlaceComments(String language);
	
	@Select("SELECT fs.id, fs.upload_dir, fs.name, fs.extension, fs.page_id, fs.create_datetime, fs.update_datetime, fs.user_id, fs.status FROM project.file_storage fs WHERE fs.page_type = ${pageType} AND fs.page_id = ${pageId}")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "place.id", column = "place_id"),
			@Result(property = "user.id", column = "user_id")})
	List<MyFile> findAllFilesByPageId(int pageType, long pageId);
	
	@Select("SELECT fs.id, fs.upload_dir, fs.name, fs.extension, fs.page_id, fs.page_type, fs.create_datetime, fs.update_datetime, fs.user_id, fs.status FROM project.file_storage fs ORDER BY fs.create_datetime DESC")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "pageType", column = "page_type", javaType = com.project.api.data.enums.LandingPageType.class, typeHandler = com.project.api.data.mapper.handler.LandingPageTypeTypeHandler.class),
			@Result(property = "user.id", column = "user_id")})
	List<LandingPageFile> findAllFiles();
	
}
