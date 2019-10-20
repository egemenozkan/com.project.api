package com.project.api.test.controller;

import static java.util.Arrays.asList;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import com.google.gson.Gson;
import com.project.api.data.enums.Language;
import com.project.api.data.model.event.EventRequest;
import com.project.api.data.model.event.EventType;
import com.project.api.test.TestConstants;

public class EventRestControllerTest {
	@Autowired
	private Gson gson;

	private static final String API_URL = "http://localhost:8090/api/v1/";

	private static final Logger logger = LoggerFactory.getLogger(UserRestControllerTest.class);

	@Test
	public void events() {
		String requestUrl = API_URL + "events";
		HttpMethod requestMethod = HttpMethod.GET;
		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
	//	restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
		EventRequest eventRequest = new EventRequest();
		eventRequest.setLanguage(Language.TURKISH);
		eventRequest.setType(EventType.CONCERT);
		restTemplate.getForEntity(requestUrl, List.class, eventRequest);
	}

	private ClientCredentialsResourceDetails resourceDetails() {
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(TestConstants.AUTH_SERVER_URL + TestConstants.TOKEN_PATH);
		resourceDetails.setClientId(TestConstants.CC_CLIENT_ID);
		resourceDetails.setClientSecret(TestConstants.CLIENT_SECRET);
		resourceDetails.setGrantType(TestConstants.GRANT);
		resourceDetails.setScope(asList(TestConstants.SCOPE));
		return resourceDetails;
	}

}
