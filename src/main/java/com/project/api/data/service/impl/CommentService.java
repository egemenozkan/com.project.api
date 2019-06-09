package com.project.api.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.enums.Status;
import com.project.api.data.mapper.CommentMapper;
import com.project.api.data.model.comment.Comment;
import com.project.api.data.model.comment.PlaceComment;
import com.project.api.data.model.comment.PlaceCommentResponse;
import com.project.api.data.service.ICommentService;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentMapper commentMapper;



	@Override
	public PlaceCommentResponse getPlaceCommentsByPlaceId(long placeId, String language) {
		PlaceCommentResponse response = new PlaceCommentResponse();
		response.setPlaceId(placeId);
		List<Comment> comments = commentMapper.findAllCommentsByPlaceId(placeId, language);

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

}
