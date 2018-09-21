package com.project.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.event.PlaceEvent;

@SuppressWarnings("rawtypes")
@RestController
//@RequestMapping(value = "/api/v1/")
public class TestRestController {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<List> getTest() {
	List<String> testList =  new ArrayList<String>();
	PlaceEvent placeEvent = new PlaceEvent(this, "Egemen");
	applicationEventPublisher.publishEvent(placeEvent);
	testList.add("Mustafa");
	testList.add("Kemal");
	testList.add("Atatürk");
System.out.println("buarada");
	ResponseEntity<List> response = new ResponseEntity<List>(testList, HttpStatus.OK);

	return response;
    }
}
