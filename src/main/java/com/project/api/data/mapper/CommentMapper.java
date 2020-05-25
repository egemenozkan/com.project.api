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

@Mapper
public interface CommentMapper {
	@Select("SELECT c.id, c.title, c.message, c.rating, c.status, c.user_id, c.username, c.first_name, c.last_name, c.email, c.place_id, c.name, c.language, c.create_datetime, c.update_datetime"
			+ " FROM project.place_comment_view c WHERE c.place_id = #{id}")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "user.id", column = "user_id"),
			@Result(property = "user.name", column = "username"),
			@Result(property = "user.firstName", column = "first_name"),
			@Result(property = "user.lastName", column = "last_name"),
			@Result(property = "user.email", column = "email"),
			@Result(property = "language", column = "language", javaType = com.project.common.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Comment> findAllPlaceCommentsByPlaceId(long id, String language);
	
	@Insert("INSERT INTO project.place_comment(title, message, user_id, place_id) VALUES(#{comment.title}, #{comment.message}, #{comment.user.id}, #{placeId})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void savePlaceComment(Comment comment, long placeId);
	
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
	
	
	@Select("SELECT c.id, c.title, c.message, c.rating, c.status, c.user_id, c.username, c.first_name, c.last_name, c.email, c.event_id, c.name, c.language, c.create_datetime, c.update_datetime"
			+ " FROM project.event_comment_view c WHERE c.event_id = #{eventId}")
	@Results(value = {@Result(property = "status", column = "status", javaType = com.project.api.data.enums.Status.class, typeHandler = com.project.api.data.mapper.handler.StatusTypeHandler.class),
			@Result(property = "user.id", column = "user_id"),
			@Result(property = "user.name", column = "username"),
			@Result(property = "user.firstName", column = "first_name"),
			@Result(property = "user.lastName", column = "last_name"),
			@Result(property = "user.email", column = "email"),
			@Result(property = "language", column = "language", javaType = com.project.common.enums.Language.class, typeHandler = com.project.api.data.mapper.handler.LanguageTypeHandler.class)})
	List<Comment> findAllEventCommentsByEventId(long eventId, String language);
	
	@Insert("INSERT INTO project.event_comment(title, message, user_id, event_id) VALUES(#{comment.title}, #{comment.message}, #{comment.user.id}, #{eventId})")
	@SelectKey(statement = "SELECT last_insert_id() as id", keyProperty = "id", keyColumn = "Id", before = false, resultType = Long.class)
	void saveEventComment(Comment comment, long eventId);
	
}
