package com.vantalii.data.service;

import java.util.List;

import com.project.api.data.model.hotel.Hotel;

public interface IHotelService {
	List<Hotel> findAllHotel();
	Hotel findHotelById(long id);
}
