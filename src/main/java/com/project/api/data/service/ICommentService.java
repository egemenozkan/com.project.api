package com.project.api.data.service;

import java.util.List;

import com.project.api.data.enums.Status;
import com.project.api.data.model.comment.Comment;
import com.project.api.data.model.comment.PlaceComment;
import com.project.api.data.model.comment.PlaceCommentResponse;

public interface ICommentService {
	Comment getCommentById(long id);

	long updateCommentStatus(long id, Status status);

	long savePlaceComment(Comment comment, long placeId);

	PlaceCommentResponse getPlaceCommentsByPlaceId(long placeId, String language);

	List<PlaceComment> getPlaceComments(String language);

}
