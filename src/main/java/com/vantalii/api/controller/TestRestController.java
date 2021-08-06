package com.vantalii.api.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.project.api.data.model.Facility;
import com.vantalii.data.service.IPlaceService;
import com.vantalii.enginee.event.service.IEventTrackingService;

@SuppressWarnings("rawtypes")
@RestController
//@RequestMapping(value = "/api/v1/")
public class TestRestController {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private Gson gson;
    @Autowired
    private IEventTrackingService eventTrackingService;
    @Autowired
    private IPlaceService placeService;
    
//    private static final String AUTH_SERVER_URL = "http://authserver:8090";
//    private static final String TOKEN_PATH = "/oauth/token";
//    private static final String AUTHORIZE_PATH = "/oauth/authorize";
    private static final String CC_CLIENT_ID  = "748547722151955";
    private static final String CLIENT_SECRET = "0906127b57b84d3e7e79ad609e33777c";
    private static final String APP_TOKEN = "7b5498718e83abbb4fec8bba2e86073c";
    private static final Logger logger = LogManager.getLogger(TestRestController.class);


    @RequestMapping(value = "/test/biletix", method = RequestMethod.GET)
    public ResponseEntity<List> getTest(@RequestParam(required = false) String q) {
	

    	try {
			eventTrackingService.collectBiletixData();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	ResponseEntity<List> response = new ResponseEntity<List>(new , HttpStatus.OK);

	return null;
    }
    @RequestMapping(value = "/tes2t", method = RequestMethod.GET)
    public ResponseEntity<List> getTestFacilietes() {
    	List<Facility> facilities = new ArrayList<>();
    	Facility facility = new Facility();
    	facility.setCode("A");
    	facility.setIcon("b");
//    	facility.setName("testFacility");
    	facilities.add(facility);
    	facilities.add(facility);
    	facilities.add(facility);
    	placeService.savePlaceFacilities(1, gson.toJson(facilities));
    	return null;
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<List> getTestFacilities() {
    	List<Facility> facilities = new ArrayList<>();
    	Facility facility = new Facility();
    	facility.setCode("A");
    	facility.setIcon("b");
//    	facility.setName("testFacility");
    	facilities.add(facility);
    	facilities.add(facility);
    	facilities.add(facility);
    	placeService.getPlaceFacilitiesByPlaceId(1);
    	return null;
    }

	public IEventTrackingService getEventTrackingService() {
		return eventTrackingService;
	}


	public void setEventTrackingService(IEventTrackingService eventTrackingService) {
		this.eventTrackingService = eventTrackingService;
	}
}
