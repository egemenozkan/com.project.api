package com.project.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.client.ResponseErrorHandler;

import com.google.gson.Gson;
@SpringBootTest
public class FacebookGraphTest {

	@Autowired
	private Gson gson;

	private static final Logger logger = LogManager.getLogger(FacebookGraphTest.class);
	private static final String AUTH_SERVER_URL = "https://graph.facebook.com";
	private static final String TOKEN_PATH = "/oauth/access_token";
	private static final String AUTHORIZE_PATH = "/oauth/authorize";
	private static final String CC_CLIENT_ID = "748547722151955";
	private static final String CLIENT_SECRET = "0906127b57b84d3e7e79ad609e33777c";
	private static final String SCOPE = "read";
	//
	// search?type=place&fields=name,checkins,picture,location,phone,website,category_list,about&q=avm,
	// turkey
	// @Test
	// public void contextLoads() {
	// }

	@Test
	public void clientCredentials() {
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
		resourceDetails.setAccessTokenUri(AUTH_SERVER_URL + TOKEN_PATH);
		resourceDetails.setClientId(CC_CLIENT_ID);
		resourceDetails.setClientSecret(CLIENT_SECRET);
		resourceDetails.setGrantType("client_credentials");
		// resourceDetails.setScope(asList("read"));

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
		// restTemplate.setMessageConverters(asList(new
		// MappingJackson2HttpMessageConverter()));
		// System.out.println("access token: " + restTemplate.get);
		System.out.println("access tokenValue: " + restTemplate.getAccessToken().getValue());
		ResponseErrorHandler reh = restTemplate.getErrorHandler();
		Object testObject = null;
		try {
			testObject = restTemplate.getForObject(
					AUTH_SERVER_URL + "/search?type=place&fields=name,checkins,picture&q=crystal", Object.class);
			reh = restTemplate.getErrorHandler();
		} catch (Exception e) {
			logger.error(":testObjectError {}", e.getMessage());
		}
		logger.debug(":testObject {}", gson.toJson(reh));
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
			OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oauth2ClientContext);
	}

}
