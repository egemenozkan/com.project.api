package com.project.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("rawtypes")
@RestController
//@RequestMapping(value = "/api/v1/")
public class TestRestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<List> getTest() {
	List<String> testList =  new ArrayList<String>();
	
	testList.add("Mustafa");
	testList.add("Kemal");
	testList.add("Atatürk");

	ResponseEntity<List> response = new ResponseEntity<List>(testList, HttpStatus.OK);

	return response;
    }
}
