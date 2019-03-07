package com.project.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@SuppressWarnings("rawtypes")
@RestController
//@RequestMapping(value = "/api/v1/")
public class TestRestController {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private Gson gson;
    
//    private static final String AUTH_SERVER_URL = "http://authserver:8090";
//    private static final String TOKEN_PATH = "/oauth/token";
//    private static final String AUTHORIZE_PATH = "/oauth/authorize";
    private static final String CC_CLIENT_ID  = "748547722151955";
    private static final String CLIENT_SECRET = "0906127b57b84d3e7e79ad609e33777c";
    private static final String APP_TOKEN = "7b5498718e83abbb4fec8bba2e86073c";
    private static final Logger logger = LogManager.getLogger(TestRestController.class);


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<List> getTest(@RequestParam String q) {
	


//	ResponseEntity<List> response = new ResponseEntity<List>(new , HttpStatus.OK);

	return null;
    }
}
