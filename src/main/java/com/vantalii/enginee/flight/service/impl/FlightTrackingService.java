package com.vantalii.enginee.flight.service.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.api.data.enums.FlightTrackingType;
import com.project.api.data.model.flight.FlightTrackingModel;
import com.vantalii.enginee.flight.service.IFlightTrackingService;
import com.vantalii.flight.enums.AirlineEnum;

@Service
public class FlightTrackingService implements IFlightTrackingService {

	private static final String DHMI_REQUEST_URL = "http://www.dhmi.gov.tr/ajaxpro/ucusBilgi,App_Web_cujpfmn5.ashx";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
	private static final Logger LOG = LogManager.getLogger(FlightTrackingService.class);

	@Override
	public List<FlightTrackingModel> getFlightList(int airportId, FlightTrackingType trackingType) {
		List<FlightTrackingModel> flightTrackingList = null;

		try {
			Document doc = Jsoup.parse(getResponse(airportId, trackingType));

			Elements ul = doc.select("ul");
			Elements li = ul.select("li"); // select all li from ul
			// System.out.println(li.text());
			if (li != null && li.size() > 2) {
				flightTrackingList = new ArrayList<FlightTrackingModel>();
				for (int i = 0; i < li.size(); i++) {
					String params[] = null;
					// System.out.println(li.get(i).select("span").text());
					if (i < 2) {
						continue;
					}
					FlightTrackingModel flight = new FlightTrackingModel();
					Elements span = li.get(i).select("span");
					for (int j = 0; j < span.size(); j++) {
						if (j == 1) {
							continue;
						}
						String param = span.get(j).text().replaceFirst("\\u00A0", "");
						if (j == 2) {
							params = param.split("\\u00A0");
						} else {
							param = param.replaceAll("\u00A0", "").trim();
						}

						switch (j) {
						case 0:
							LocalDate flightDate = LocalDate.parse(param, formatter);
							flight.setFlightDate(flightDate);
							break;
						case 2:
							if (params != null) {
								AirlineEnum airline = null;
								try {
									airline = AirlineEnum.getByIata(params[0]);
									flight.setAirline(airline.getAirline());
								} catch (Exception e) {
									LOG.warn("::airlineEnum {}", e.getMessage());
								}

								flight.setAirlineCode(params[0]);
							}
							flight.setFlightNo(param);
							break;
						case 3:
							if (FlightTrackingType.DOMESTIC_ARRIVAL == trackingType
									|| FlightTrackingType.INTERNATIONAL_ARRIVAL == trackingType) {
								flight.setDeptAirport(param);
							} else {
								flight.setArrAirport(param);
							}
							break;
						case 4:

							if (param.length() > 0) {
								LocalDateTime scheduledTime = flight.getFlightDate().atTime(
										Integer.parseInt(param.substring(0, 2)), Integer.parseInt(param.substring(3)));
								flight.setScheduledTime(scheduledTime);
							}
							break;
						case 5:
							if (param.length() > 0) {
								LocalDateTime estimatedTime = flight.getFlightDate().atTime(
										Integer.parseInt(param.substring(0, 2)), Integer.parseInt(param.substring(3)));
								flight.setEstimatedTime(estimatedTime);
							}
							break;
						case 6:
							if (FlightTrackingType.DOMESTIC_ARRIVAL == trackingType
									|| FlightTrackingType.INTERNATIONAL_ARRIVAL == trackingType) {
								flight.setBelt(param);
							} else {
								flight.setGate(param);
							}
							break;
						case 7:
							flight.setRemark(param);
							break;
						case 8:
							flight.setTerminal(param);
							break;

						default:
							break;
						}

					}
					flight.setAirlineLogo("http://files.sevais.com/flight/logos/airlines/TK.png");
					flightTrackingList.add(flight);
				}
			}
		} catch (Exception e) {
			LOG.warn("::FlightTrackingService/getFlightList {}", e);

		}
		return flightTrackingList;
	}

	private String getResponse(int airportId, FlightTrackingType trackingType) {
		URL url;
		HttpURLConnection con;
		StringBuffer responseContentBuf = null;
		String responseContent = "";
		try {
			url = new URL(DHMI_REQUEST_URL);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Connection", "keep-alive");
			con.setRequestProperty("X-AjaxPro-Method", "getFile");
			con.setRequestProperty("Content-Type", "text/plain; charset=UTF-8");
			con.setRequestProperty("Accept", "*/*");
			con.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
			con.setRequestProperty("Accept-Encoding", "gzip, deflate");
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.9,tr;q=0.8");
			con.setRequestProperty("Referer", "http://www.dhmi.gov.tr/ucusbilgi.aspx");
			con.setDoOutput(true);
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);

			DataOutputStream os = new DataOutputStream(con.getOutputStream());
			os.writeBytes(getRequestFile(airportId, trackingType));
			os.flush();
			os.close();

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
				//
			}

		} catch (IOException e) {
			LOG.warn("::FlightTrackingService {}", e);
		}
		responseContent = responseContentBuf != null
				? responseContentBuf.toString().replace("\"", "").replace("\\", "\"")
				: null;
		return responseContent;
	}

	private String getFlightTrackingTypeText(FlightTrackingType trackingType) {
		String flightTrackingTypeText = null;
		switch (trackingType) {
		case DOMESTIC_ARRIVAL:
			flightTrackingTypeText = "domarr";
			break;
		case DOMESTIC_DEPARTURE:
			flightTrackingTypeText = "domdep";
			break;
		case INTERNATIONAL_ARRIVAL:
			flightTrackingTypeText = "intarr";
			break;
		case INTERNATIONAL_DEPARTURE:
			flightTrackingTypeText = "intdep";
			break;
		default:
			break;
		}
		return flightTrackingTypeText;

	}

	private String getRequestFile(int airportId, FlightTrackingType trackingType) {
		StringBuffer requestUrlBuf = new StringBuffer("UcusBilgileri/");
		requestUrlBuf.append(Integer.toString(airportId));
		requestUrlBuf.append("/");
		requestUrlBuf.append(getFlightTrackingTypeText(trackingType));
		requestUrlBuf.append(".txt");
		String type = (trackingType == FlightTrackingType.DOMESTIC_ARRIVAL
				|| trackingType == FlightTrackingType.INTERNATIONAL_ARRIVAL) ? "1" : "2";
		String ic = (trackingType == FlightTrackingType.DOMESTIC_ARRIVAL
				|| trackingType == FlightTrackingType.DOMESTIC_DEPARTURE) ? "1" : "2";
		String requestFile = "{\"file\":\"" + requestUrlBuf.toString() + "\",\"type\":" + type
				+ ",\"caption\":\"\",\"ic\":" + ic + ",\"hv\":" + airportId + "}";
		return requestFile;
	}
}
