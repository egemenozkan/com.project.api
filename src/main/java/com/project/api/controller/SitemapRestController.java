package com.project.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.api.data.model.place.Place;
import com.project.api.data.service.ISiteMapService;

@RestController
@RequestMapping(value = "/api/v1/")
public class SitemapRestController {

	@Autowired
	private Gson gson;

	@Autowired
	ISiteMapService siteMapService;
	
	private static final Logger LOG = LogManager.getLogger(SitemapRestController.class);



	@GetMapping(value = "/updatePlaceSlugs")
	public ResponseEntity<List<Place>> findAllPlace(@RequestParam(defaultValue = "RU", required = false) String language,
			@RequestParam(required = false) String mainType, @RequestParam(required = false) String type) {
		
		siteMapService.updatePlaceSlugs();
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}


}
