package com.project.api.data.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.api.data.enums.VenueType;
import com.project.api.data.model.common.Venue;
import com.project.api.data.service.IVenueService;
import com.project.common.BaseService;

@Service
public class VenueService extends BaseService implements IVenueService {

    @Override
    public int saveVenue(Venue venue) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Venue getVenueById(int venueId) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public List<Venue> getVenuesByType(VenueType venueType) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void syncVenue(VenueType venueType, int selfId) {
	// TODO Auto-generated method stub
	
    }
/*
 * 
 * IN p_venue_id INTEGER, IN p_venue_name VARCHAR(30), IN p_venue_type INTEGER,
IN p_city_id INTEGER, IN p_region_id INTEGER, IN p_subregion_id INTEGER,
IN p_address_line_1 VARCHAR(90), IN p_address_line_2 VARCHAR(90),IN p_post_code VARCHAR(15),
IN p_description VARCHAR(90)(non-Javadoc)
 * @see com.sevais.opetra.service.db.IVenueService#saveVenue(com.sevais.online.model.Venue)
 */
//    @Override
//    public int saveVenue(Venue venue) {
//	int result = this.callInteger("PKG_VENUE__P_SAVE_VENUE", venue.getId(), venue.getName(), venue.getType().getId(), venue.getAddress().getLocation().getCityId(),
//		venue.getAddress().getLocation().getRegionId(), venue.getAddress().getLocation().getSubregionId(), venue.getAddress().getAddressLine1(),
//		venue.getAddress().getAddressLine2(), venue.getAddress().getPostCode(), venue.getAddress().getDescription());
//	return result;
//    }
//
//    @Override
//    public Venue getVenueById(int venueId) {
//	Venue result = (Venue) this.callRow("PKG_VENUE__P_GET_VENUE_BY_ID_1", new VenueRowMapper(), venueId);
//	return result;
//    }
//
//    @Override
//    public List<Venue> getVenuesByType(VenueType venueType) {
//	List<Venue> result = (List<Venue>) this.callList("PKG_VENUE__P_GET_VENUE_BY_TYPE_ID_1", new VenueRowMapper(), venueType.getId());
//	return result;
//    }
//
//    @Override
//    public void syncVenue(VenueType venueType, int selfId) {
//	this.callInteger("PKG_VENUE__P_SYNC_VENUE", venueType.getId(), selfId);
//	
//    }

}
