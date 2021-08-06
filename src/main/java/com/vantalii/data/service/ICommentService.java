package com.vantalii.data.service;

import java.util.List;

import com.project.api.data.enums.Status;
import com.project.api.data.model.comment.Comment;
import com.project.api.data.model.comment.CommentResponse;
import com.project.api.data.model.comment.PlaceComment;

public interface ICommentService {
	Comment getCommentById(long id);

	long updateCommentStatus(long id, Status status);

	long savePlaceComment(Comment comment, long placeId);

	CommentResponse getPlaceCommentsByPlaceId(long id, String language);
	
	CommentResponse getEventCommentsByEventId(long id, String language);
	
	long saveEventComment(Comment comment, long eventId);

	List<PlaceComment> getPlaceComments(String language);

}
