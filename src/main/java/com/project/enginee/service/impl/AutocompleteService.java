package com.project.enginee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.project.common.model.AutocompleteData;
import com.project.common.model.AutocompleteResponse;
import com.project.enginee.service.IAutocompleteService;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Place;

public class AutocompleteService implements IAutocompleteService {

    @Autowired
    private Gson gson;
    
    private static final Logger logger = LogManager.getLogger(AutocompleteService.class);
    
    
    private static final String APP_ID  = "748547722151955";
    private static final String APP_SECRET = "0906127b57b84d3e7e79ad609e33777c";
    private static final String APP_TOKEN = "7b5498718e83abbb4fec8bba2e86073c";
	
	@Override
	public AutocompleteResponse searchPlace(String query) {
		AutocompleteResponse autocompleteResponse = new AutocompleteResponse();
		
		AccessToken accessToken = new DefaultFacebookClient(Version.VERSION_3_1).obtainAppAccessToken(APP_ID, APP_SECRET);
		DefaultFacebookClient facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_3_1);

		try {

			Connection<Place> publicSearch = facebookClient.fetchConnection("search", Place.class,
					    Parameter.with("q", query),
					    Parameter.with("type", "place"),
//					    Parameter.with("center", "38.734802,35.467987"),
//					    Parameter.with("distance", 400000),
					    Parameter.with("fields", "about, name, category_list, checkins, rating_count, overall_star_rating, location, is_verified"));
			
			List<Place> places=  publicSearch.getData();
			List<AutocompleteData> data =  new ArrayList<>();
			
			for (Place place : places) {
				AutocompleteData tempDatum = new AutocompleteData();
				tempDatum.setLabel(place.getName());
				tempDatum.setValue(place.getId());
				
			}
			autocompleteResponse.setData(data);
			
			logger.warn(places.size() + " " + gson.toJson(autocompleteResponse));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}


}
