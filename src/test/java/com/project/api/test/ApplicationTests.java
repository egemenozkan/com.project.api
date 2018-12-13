package com.project.api.test;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.Place;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = "com.project")
public class ApplicationTests {

    @Autowired
    private Gson gson;
    
    private static final Logger logger = LogManager.getLogger(ApplicationTests.class);
//    private static final String AUTH_SERVER_URL = "http://authserver:8090";
//    private static final String TOKEN_PATH = "/oauth/token";
//    private static final String AUTHORIZE_PATH = "/oauth/authorize";
    private static final String CC_CLIENT_ID  = "748547722151955";
    private static final String CLIENT_SECRET = "0906127b57b84d3e7e79ad609e33777c";
    private static final String APP_TOKEN = "7b5498718e83abbb4fec8bba2e86073c";
//    private static final String SCOPE = "read";
//    // https://developers.facebook.com/docs/places/web/search
    // https://medium.com/@damithchathuranga/spring-4-3-event-listener-abd47c8b9891
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

	@Test
	public void contextLoads() {
		
		AccessToken accessToken = new DefaultFacebookClient(Version.VERSION_3_1).obtainAppAccessToken(CC_CLIENT_ID, CLIENT_SECRET);
		DefaultFacebookClient facebookClient = new DefaultFacebookClient(accessToken.getAccessToken(), Version.VERSION_3_1);
		logger.warn("accessToken: {}", gson.toJson(accessToken));
		try {
			
			Connection<Place> publicSearch = facebookClient.fetchConnection("search", Place.class,
					    Parameter.with("q", "Crystal W"), Parameter.with("type", "place"),
					    Parameter.with("center", "38.734802,35.467987"),
					    Parameter.with("distance", 400000),
					    Parameter.with("fields", "about, name, category_list, checkins, rating_count, overall_star_rating, location, is_verified"));
			List<Place> places=  publicSearch.getData();
			//PlaceEvent placeEvent = new PlaceEvent(this, places);
		//	applicationEventPublisher.publishEvent(placeEvent);
			logger.warn(places.size() + " " + gson.toJson(places));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	//@Test
	public void clientCredentials() {
		ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setAccessTokenUri(TestConstants.AUTH_SERVER_URL + TestConstants.TOKEN_PATH);
        resourceDetails.setClientId(TestConstants.CC_CLIENT_ID);
        resourceDetails.setClientSecret(TestConstants.CLIENT_SECRET);
        resourceDetails.setGrantType("client_credentials");
        resourceDetails.setScope(asList("read"));

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
      //  restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
        System.out.println("access token: " + restTemplate.getAccessToken());
        String testObject = restTemplate.getForObject(TestConstants.AUTH_SERVER_URL + "/test", String.class);
        logger.debug(":testObject {}", gson.toJson(testObject));
	}

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext,
	        OAuth2ProtectedResourceDetails details) {
	    return new OAuth2RestTemplate(details, oauth2ClientContext);
	}

}
