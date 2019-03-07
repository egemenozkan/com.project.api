package com.project.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.api.data.enums.Status;
import com.project.api.data.model.comment.Comment;
import com.project.api.data.model.comment.PlaceComment;
import com.project.api.data.model.comment.PlaceCommentResponse;
import com.project.api.data.service.ICommentService;
import com.project.api.data.service.IPlaceService;

@RestController
@RequestMapping(value = "/api/v1/places")
public class PlaceCommentRestController {

	private static final Logger LOG = LogManager.getLogger(PlaceCommentRestController.class);

	@Autowired
	private Gson gson;

	@Autowired
	private ICommentService commentService;
	@Autowired
	private IPlaceService placeService;

	@RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
	public ResponseEntity<PlaceCommentResponse> getPlaceCommentResponse(@PathVariable long id,
			@RequestParam(defaultValue = "RU", required = false) String language) {

		PlaceCommentResponse placeCommentResponse = commentService.getPlaceCommentsByPlaceId(id, language);

		ResponseEntity<PlaceCommentResponse> responseEntity = new ResponseEntity<>(placeCommentResponse, HttpStatus.OK);

		if (LOG.isDebugEnabled()) {
			LOG.debug("::getPlaceCommentResponse {}", gson.toJson(placeCommentResponse));
		}
		return responseEntity;
	}

	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public ResponseEntity<List<PlaceComment>> getPlaceComments(@RequestParam(defaultValue = "RU", required = false) String language) {

		List<PlaceComment> placeComments = commentService.getPlaceComments(language);

		ResponseEntity<List<PlaceComment>> responseEntity = new ResponseEntity<List<PlaceComment>>(placeComments, HttpStatus.OK);

		if (LOG.isDebugEnabled()) {
			LOG.debug("::getPlaceComments {}", gson.toJson(placeComments));
		}
		return responseEntity;
	}

	@GetMapping(value = "/comments/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable long id) {

		Comment comment = commentService.getCommentById(id);

		ResponseEntity<Comment> responseEntity = new ResponseEntity<Comment>(comment, HttpStatus.OK);

		if (LOG.isDebugEnabled()) {
			LOG.debug("::getCommentById id: {} response: {}", id, gson.toJson(comment));
		}
		return responseEntity;
	}

	@PostMapping(value = "/{id}/comments")
	public ResponseEntity<Long> savePlaceComment(@PathVariable long id, RequestEntity<Comment> requestEntity) {

		Comment comment = requestEntity.getBody();
		if (id > 0) {
			commentService.savePlaceComment(comment, id);
		}
		ResponseEntity<Long> responseEntity = new ResponseEntity<>(comment.getId(), HttpStatus.OK);

		if (LOG.isDebugEnabled()) {
			LOG.debug("::getCommentById id: {} response: {}", id, gson.toJson(comment));
		}
		return responseEntity;
	}

	@PostMapping(value = "/comments/{id}/status")
	public ResponseEntity<Long> updateStatus(@PathVariable long id, RequestEntity<Status> requestEntity) {
		Status status = requestEntity.getBody();
		commentService.updateCommentStatus(id, status);
		return null;

	}
}
