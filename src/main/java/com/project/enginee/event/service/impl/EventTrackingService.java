package com.project.enginee.event.service.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.api.data.enums.FeeType;
import com.project.api.data.enums.PeriodType;
import com.project.api.data.model.event.Event;
import com.project.api.data.model.event.EventType;
import com.project.api.data.model.place.Localisation;
import com.project.api.data.model.place.Place;
import com.project.api.data.service.IEventService;
import com.project.common.enums.Language;
import com.project.enginee.event.service.BiletixVenueEnum;
import com.project.enginee.event.service.IEventTrackingService;

@Service
public class EventTrackingService implements IEventTrackingService {

	private static final String BILETIX_ANTALYA_REQUEST_URL = "https://www.biletix.com/solr/tr/select/?start=0&rows=1300&q=*:*&fq=start%3A%5B2019-10-04T00%3A00%3A00Z%20TO%202021-10-04T00%3A00%3A00Z%2B1DAY%5D&sort=start%20asc,%20vote%20desc&&fq=city:%22Antalya%22&wt=json&indent=true&facet=true";
	private static final Logger LOG = LogManager.getLogger(EventTrackingService.class);

	@Autowired
	private Gson gson;

	@Autowired
	private IEventService eventService;

	@Override
	public void collectBiletixData() throws ParserConfigurationException, IOException, ParseException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		try {
			URL url = new URL(BILETIX_ANTALYA_REQUEST_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			StringBuffer responseContentBuf = null;
			String responseContent = "";
			con.setRequestMethod("GET");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
			con.setRequestProperty("Accept",
					"text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, */*; q=0.01");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,tr;q=0.8");
			con.setRequestProperty("Referer",
					"http://www.biletix.com/search/TURKIYE/tr?category_sb=-1&date_sb=-1&city_sb=Antalya");
			con.setRequestProperty("Cookie",
					"BXID=AAAAAAXLzOaLhNhRXxSJ5z3gukk37XigvhqKzRvtPXMvkkA+Tw==; path=/; domain=biletix.com");
//			con.setInstanceFollowRedirects(true);  //you still need to handle redirect manully.
//			HttpURLConnection.setFollowRedirects(true);
//			con.setDoOutput(true);
			con.connect();

			int status = con.getResponseCode();
			if (status == HttpStatus.OK.value()) {

				Reader reader = null;
				if ("gzip".equals(con.getContentEncoding())) {
					reader = new InputStreamReader(new GZIPInputStream(con.getInputStream()), "UTF-8");
				} else {
					reader = new InputStreamReader(con.getInputStream(), "UTF-8");
				}
				responseContentBuf = new StringBuffer();
				Scanner input = new Scanner(reader);
				while (input.hasNextLine()) {
					// System.out.println(input.nextLine());
					responseContentBuf.append(input.nextLine());
				}
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(responseContentBuf.toString());
				JSONObject response = (JSONObject) jsonObject.get("response");
				List<JSONObject> rows = (List<JSONObject>) response.get("docs");
				int index = 0;
				for (JSONObject row : rows) {
					if (BiletixVenueEnum.getIdByVenueCode(row.get("venuecode").toString()) > 0) {
						StringBuilder biletixUniqueKey = new StringBuilder();
						biletixUniqueKey.append(row.get("venuecode"));
						biletixUniqueKey
								.append(row.get("name").toString().toLowerCase().strip().replaceAll("\\s+", ""));

						Event event = eventService.findByBiletixId(biletixUniqueKey.toString());
						if (event == null) {
							event = new Event();
						}
						event.setPlace(new Place(BiletixVenueEnum.getIdByVenueCode(row.get("venuecode").toString())));
						event.setName(row.get("name").toString().trim());
						event.setLanguage(Language.TURKISH);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
						LocalDateTime startDateTime = LocalDateTime.parse(row.get("start").toString(), formatter)
								.plusHours(3);
						event.setStartDate(startDateTime.toLocalDate());
						event.setStartTime(startDateTime.toLocalTime());
						event.setDuration(120);
						event.setPeriodType(PeriodType.ONEDAY);

						event.setBiletixId(biletixUniqueKey.toString());
						event.setFeeType(FeeType.ENTRY_FEE);
						String category = row.get("category").toString();
						String subcategory = row.get("subcategory").toString();

						if (category != null && subcategory != null) {
							if ("MUSIC".equals(category)) {
								event.setType(EventType.CONCERT);
							} else if ("MUSEUM".equals(category)) {
								event.setType(EventType.MUSEUMS);
							} else if ("FAMILY".equals(category)) {
								event.setType(EventType.FAMILY_SHOWS);
							} else if ("ART".equals(category)) {
								if ("stand_up$ART".equals(subcategory)) {
									event.setType(EventType.STAND_UP);
								} else if ("bale_dans$ART".equals(subcategory)) {
									event.setType(EventType.DANCE_AND_BALLET);
								} else {
									event.setType(EventType.THEATER);
								}
							}
						}
						event.setDescription(row.get("description").toString().trim());
						Map<String, Localisation> localisationMap = new HashMap<String, Localisation>();
						Localisation english = new Localisation();
						english.setLanguage(Language.ENGLISH);
						english.setName(row.get("name").toString().trim());
						localisationMap.put(Language.ENGLISH.toString(), english);
						Localisation russian = new Localisation();
						russian.setLanguage(Language.RUSSIAN);
						russian.setName(row.get("name").toString().trim());
						localisationMap.put(Language.RUSSIAN.toString(), russian);
						event.setLocalisation(localisationMap);
						eventService.saveEvent(event);
//
//						LOG.error("promoter {} venuecode {} name {}", row.get("promoter"),
//								row.get("venuecode").toString(), row.get("name").toString().trim());
					} else {
						LOG.error("not found venuecode {} {}", row.get("venuecode"), gson.toJson(row));
					}
				}
				//
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
