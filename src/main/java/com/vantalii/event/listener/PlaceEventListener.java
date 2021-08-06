package com.vantalii.event.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.google.gson.Gson;
import com.project.api.data.model.common.MyPlace;
import com.vantalii.api.data.mapper.PlaceMapper;
import com.vantalii.event.PlaceEvent;

@Component
public class PlaceEventListener {
    @Autowired
    private Gson gson;
    
    @Autowired
    private PlaceMapper placeMapper;
    
    private static final Logger LOG = LogManager.getLogger(PlaceEventListener.class);

    @Async
    @EventListener
    public void handlePlaceEvent(PlaceEvent event) throws InterruptedException {
	if (event != null && !CollectionUtils.isEmpty(event.getPlaces())) {
	    for (MyPlace _place : event.getPlaces()) {
		placeMapper.saveFacebookPlace(_place);

		System.out.println("---");

	    }


	}

    }

}
