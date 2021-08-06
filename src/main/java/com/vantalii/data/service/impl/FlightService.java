package com.vantalii.data.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.model.flight.Airport;
import com.vantalii.api.data.mapper.AirportMapper;
import com.vantalii.data.service.IFlightService;

@Service
public class FlightService implements IFlightService {
	private static final Logger LOG = LogManager.getLogger(FlightService.class);

	@Autowired
	private AirportMapper airportMapper;
	
	@Override
	public List<Airport> getAirports() {
	    
	    return airportMapper.findAllAirports();
	}

	@Override
	public int saveAirport(Airport airport) {
	    // TODO Auto-generated method stub
	    return 0;
	}

	@Override
	public void saveAirports(List<Airport> airports) {
	    // TODO Auto-generated method stub
	    
	}

//	@Override
//	public List<Airport> getAirports() {
//		List<Airport> result = (List<Airport>) this
//				.callList("PKG_FLIGHT__P_GET_AIRPORTS", new AirportRowMapper());
//
//
//		return result;
//	}
//
//	@Override
//	public int saveAirport(Airport airport) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void saveAirports(List<Airport> airports) {
//		Connection connection = null;
//		PreparedStatement ps = null;
//		try {
//			connection = dataSource.getConnection();
//
//			ps = connection.prepareStatement("CALL PKG_FLIGHT__P_SAVE_AIRPORT(?,?,?,?,?,?,?)");
//
//			for (Airport airport : airports) {
//				prepareDBClient(ps);
//				ps.setInt(7, airport.getDhmId());
//				//ps.setString(8,)
//				ps.addBatch();
//			}
//			ps.executeBatch(); // insert remaining records
//		} catch (SQLException e) {
//			LOG.warn(":saveAirports  {}", e.getMessage());
//			e.printStackTrace();
//		} finally {
//			try {
//				ps.close();
//				connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				LOG.warn(":saveAirports  {}", e.getMessage());
//			}
//		}
//
//	}

}
