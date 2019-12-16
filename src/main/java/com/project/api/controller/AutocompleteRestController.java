package com.project.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.api.data.model.autocomplete.AutocompleteRequest;
import com.project.api.data.model.autocomplete.AutocompleteResponse;
import com.project.api.service.IAutocompleteService;

@RestController
@RequestMapping(value = "/api/v1/")
public class AutocompleteRestController {

	@Autowired
	private IAutocompleteService autocompleteService;

	@PostMapping("/autocomplete")
	public ResponseEntity<AutocompleteResponse> autocomplete(RequestEntity<AutocompleteRequest> requestEntity) {

		AutocompleteResponse autocompleteResponse = autocompleteService.autocomplete(requestEntity.getBody());
		return new ResponseEntity<>(autocompleteResponse, HttpStatus.OK);
	}
}
