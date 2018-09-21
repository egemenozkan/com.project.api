package com.project.event.listener;

import java.util.concurrent.TimeUnit;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.project.event.PlaceEvent;

@Component
public class PlaceEventListener{
//	@Async
	@TransactionalEventListener
	public void handlePlaceEvent(PlaceEvent event) throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
//		for (int i = 0; i< 1000000; i++)
			System.out.println("test event" + event.getName());
			TimeUnit.SECONDS.sleep(5);
			System.out.println("test event bitti" + event.getName());

	}

	
}
