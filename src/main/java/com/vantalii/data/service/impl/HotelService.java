package com.vantalii.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.api.data.model.hotel.Hotel;
import com.vantalii.api.data.mapper.HotelMapper;
import com.vantalii.data.service.IHotelService;

@Service
public class HotelService implements IHotelService {

	@Autowired
	private Gson gson;


	@Autowired
	private HotelMapper hotelMapper;
	
	@Override
	public List<Hotel> findAllHotel() {
		List<Hotel> hotels = hotelMapper.findAllHotel();
		return hotels;
	}

	@Override
	public Hotel findHotelById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
