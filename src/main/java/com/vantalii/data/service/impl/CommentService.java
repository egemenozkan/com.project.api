package com.vantalii.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.enums.Status;
import com.project.api.data.model.comment.Comment;
import com.project.api.data.model.comment.CommentResponse;
import com.project.api.data.model.comment.PlaceComment;
import com.vantalii.api.data.mapper.CommentMapper;
import com.vantalii.data.service.ICommentService;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public CommentResponse getPlaceCommentsByPlaceId(long placeId, String language) {
		CommentResponse response = new CommentResponse();
		response.setId(placeId);
		List<Comment> comments = commentMapper.findAllPlaceCommentsByPlaceId(placeId, language);

		if (comments != null && !comments.isEmpty()) {
			response.setComments(comments);
			response.setSuccess(true);
		} else {
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public List<PlaceComment> getPlaceComments(String language) {
		List<PlaceComment> placeComments = commentMapper.findAllPlaceComments(language);
		return placeComments;
	}

	@Override
	public long savePlaceComment(Comment comment, long placeId) {
		commentMapper.savePlaceComment(comment, placeId);
		return comment.getId();
	}

	@Override
	public Comment getCommentById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long updateCommentStatus(long id, Status status) {
		commentMapper.updateCommentStatus(id, status.getId());
		return 0;
	}

	@Override
	public CommentResponse getEventCommentsByEventId(long eventId, String language) {
		CommentResponse response = new CommentResponse();
		response.setId(eventId);
		List<Comment> comments = commentMapper.findAllEventCommentsByEventId(eventId, language);

		if (comments != null && !comments.isEmpty()) {
			response.setComments(comments);
			response.setSuccess(true);
		} else {
			response.setSuccess(false);
		}
		return response;
	}

	@Override
	public long saveEventComment(Comment comment, long eventId) {
		commentMapper.saveEventComment(comment, eventId);
		return comment.getId();
	}

}
