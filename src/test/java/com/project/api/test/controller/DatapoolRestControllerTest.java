package com.project.api.test.controller;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import com.google.gson.Gson;
import com.project.api.data.model.gis.City;
import com.project.api.data.model.gis.Country;
import com.project.api.data.model.gis.Region;
import com.project.api.data.model.gis.Subregion;
import com.project.api.test.TestConstants;
import com.project.common.model.Nationality;

@SpringBootTest
public class DatapoolRestControllerTest {

	@Autowired
	private Gson gson;

	private static final String API_URL = "http://localhost:8090/api/v1/";

	private static final Logger logger = LoggerFactory.getLogger(DatapoolRestControllerTest.class);

	@Test
	public void getNationalities() {
		String requestUrl = API_URL + "nationalities";
		HttpMethod requestMethod = HttpMethod.GET;
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
		List<Nationality> nationalities = restTemplate.getForObject(requestUrl, List.class);

		assertNotNull(nationalities);

		logger.debug(":{} testResult {}", requestUrl, gson.toJson(nationalities));
	}

	@Test
	public void getCountries() {
		String requestUrl = API_URL + "countries";
		HttpMethod requestMethod = HttpMethod.GET;
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
		List<Country> countries = restTemplate.getForObject(requestUrl, List.class);

		assertNotNull(countries);

		logger.debug(":{} testResult {}", requestUrl, gson.toJson(countries));
	}

	@Test
	public void getCitiesByCountryId() {
		int countryId = 223; // Turkey
		String requestUrl = API_URL + "countries/{countryId}/cities";
		HttpMethod requestMethod = HttpMethod.GET;
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
		List<City> cities = restTemplate.getForObject(requestUrl, List.class, countryId);

		assertNotNull(cities);

		logger.debug(":{} testResult {}", requestUrl, gson.toJson(cities));
	}

	@Test
	public void getRegionsByCityId() {
		int cityId = 1; // Antalya
		String requestUrl = API_URL + "cities/{cityId}/regions";
		HttpMethod requestMethod = HttpMethod.GET;
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
		List<Region> regions = restTemplate.getForObject(requestUrl, List.class, cityId);

		assertNotNull(regions);

		logger.debug(":{} testResult {}", requestUrl, gson.toJson(regions));
	}

	@Test
	public void getSubregionsByRegionId() {
		int regionId = 1; // Antalya
		String requestUrl = API_URL + "regions/{regionId}/subregions";
		HttpMethod requestMethod = HttpMethod.GET;
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
		restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
		List<Subregion> subregions = restTemplate.getForObject(requestUrl, List.class, regionId);

		assertNotNull(subregions);

		logger.debug(":{} testResult {}", requestUrl, gson.toJson(subregions));
	}
//    @Bean
//    public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
//	return new OAuth2RestTemplate(details, oauth2ClientContext);
//    }

	private ClientCredentialsResourceDetails resourceDetails() {
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(TestConstants.AUTH_SERVER_URL + TestConstants.TOKEN_PATH);
		resourceDetails.setClientId(TestConstants.CC_CLIENT_ID);
		resourceDetails.setClientSecret(TestConstants.CLIENT_SECRET);
		resourceDetails.setGrantType("authorization_code");
		resourceDetails.setScope(asList("read"));
		return resourceDetails;
	}

}
