package com.project.api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestConstants {
    public static final Logger logger = LogManager.getLogger(ApplicationTests.class);
    public static final String AUTH_SERVER_URL = "http://localhost:8090";
    public static final String TOKEN_PATH = "/oauth/token";
    public static final String AUTHORIZE_PATH = "/oauth/authorize";
    public static final String CC_CLIENT_ID  = "trusted-app";
    public static final String GRANT = "client_credentials";
    public static final String CLIENT_SECRET = "secret";
    public static final String SCOPE = "places events users";
    
}
