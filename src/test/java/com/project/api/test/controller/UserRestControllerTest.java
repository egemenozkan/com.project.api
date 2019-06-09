package com.project.api.test.controller;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import com.google.gson.Gson;
import com.project.api.test.TestConstants;
import com.project.common.model.User;

@SpringBootTest
public class UserRestControllerTest {

    @Autowired
    private Gson gson;

    private static final String API_URL = "http://localhost:8090/api/v1/";

    private static final Logger logger = LoggerFactory.getLogger(UserRestControllerTest.class);

    @Test
    public void createUser() {
	String requestUrl = API_URL + "users";
	HttpMethod requestMethod = HttpMethod.POST;
	DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

	OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
	restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
	
	User user = new User();
	user.setFirstName("Egemen");
	user.setLastName("ÖZKAN");
	user.setEmail("egemenozkan@gmail.com");
	user.setPassword("1234");
	
	Long id = restTemplate.postForObject(requestUrl, user, Long.class);

	assertNotNull(id);

	logger.debug(":{} testResult {}", requestUrl, gson.toJson(id));
    }
    
    @Test
    public void getUserById() {
	String requestUrl = API_URL + "users/{id}";
	HttpMethod requestMethod = HttpMethod.POST;
	Long id = 8L;
	DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

	OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
	restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
	

	
	User user = restTemplate.getForObject(requestUrl, User.class, id);

	assertNotNull(user);

	logger.debug(":{} testResult {}", requestUrl, gson.toJson(user));
    }
  
    
    
    @Test
    public void updateUser() {
	String requestUrl = API_URL + "users";
	HttpMethod requestMethod = HttpMethod.PUT;
	DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

	OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
	restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
	
	User user = new User();
	user.setId(8L);
	user.setFirstName("EgemenT2");
	user.setLastName("ÖZKAN");
	user.setEmail("egemenozkan@gmail.com");
	user.setPassword("1234");
	
	HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
  
        // Data attached to the request.
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
	
	ResponseEntity<User> userResponse = restTemplate.exchange(requestUrl, requestMethod, requestBody, User.class);
	logger.debug(":{} testResult {}", requestUrl, gson.toJson(userResponse));
	assertNotNull(userResponse);

    }
    
    @Test
    public void existsByEmail() {
	String requestUrl = API_URL + "users/available/email/{email}";
	HttpMethod requestMethod = HttpMethod.GET;
	DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

	OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails(), clientContext);
	restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
	String email = "egemenozkan@gmail.com";
	
	Boolean responseObject = restTemplate.getForObject(requestUrl, Boolean.class, email);
	logger.debug(":{} testResult {}", requestUrl, gson.toJson(responseObject));
	assertNotNull(responseObject);

    }
    
    
    private ClientCredentialsResourceDetails resourceDetails() {
	ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
	resourceDetails.setAccessTokenUri(TestConstants.AUTH_SERVER_URL + TestConstants.TOKEN_PATH);
	resourceDetails.setClientId(TestConstants.CC_CLIENT_ID);
	resourceDetails.setClientSecret(TestConstants.CLIENT_SECRET);
	resourceDetails.setGrantType("client_credentials");
	resourceDetails.setScope(asList("read"));
	return resourceDetails;
    }

}
