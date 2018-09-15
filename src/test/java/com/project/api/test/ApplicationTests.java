package com.project.api.test;

import static java.util.Arrays.asList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private Gson gson;
    
    private static final Logger logger = LogManager.getLogger(ApplicationTests.class);
//    private static final String AUTH_SERVER_URL = "http://authserver:8090";
//    private static final String TOKEN_PATH = "/oauth/token";
//    private static final String AUTHORIZE_PATH = "/oauth/authorize";
//    private static final String CC_CLIENT_ID  = "trusted-app";
//    private static final String CLIENT_SECRET = "secret";
//    private static final String SCOPE = "read";
//    
    
	@Test
	public void contextLoads() {
	}
	
	@Test
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
