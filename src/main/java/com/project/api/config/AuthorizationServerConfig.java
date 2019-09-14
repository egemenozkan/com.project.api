package com.project.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created by nydiarra on 06/05/17.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${security.jwt.client-id}")
	private String clientId;

	@Value("${security.jwt.client-secret}")
	private String clientSecret;

	@Value("${security.jwt.grant-type}")
	private String grantType;

	@Value("${security.jwt.scope-read}")
	private String scopeRead;

	@Value("${security.jwt.scope-write}")
	private String scopeWrite = "write";

	@Value("${security.jwt.resource-ids}")
	private String resourceIds;

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private JwtAccessTokenConverter accessTokenConverter;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@Override
//	public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
//		configurer
//		        .inMemory()
//		        .withClient(clientId)
//				.secret(passwordEncoder.encode(clientSecret))
//		        .authorizedGrantTypes(grantType)
//		        .scopes(scopeRead, scopeWrite)
//		        .resourceIds(resourceIds);
//	}

	@Override

	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
		.withClient("normal-app")
			.authorizedGrantTypes("authorization_code", "implicit")
			.authorities("ROLE_CLIENT")
			.scopes("places", "events", "users", "transfer")
//			.resourceIds(resourceIds)
			.accessTokenValiditySeconds(36000000)
			.refreshTokenValiditySeconds(36000000)
	.and()
		.withClient("trusted-app")
			.authorizedGrantTypes("client_credentials", "password", "refresh_token")
			.authorities("ROLE_TRUSTED_CLIENT")
			.scopes("places", "events", "users", "transfer")
//			.resourceIds(resourceIds)
			.accessTokenValiditySeconds(36000000)
			.refreshTokenValiditySeconds(36000000)
			.secret(passwordEncoder.encode("secret"))
	.and()
		.withClient("android-app")
			.authorizedGrantTypes("password", "refresh_token")
			.authorities("ROLE_TRUSTED_CLIENT", "ROLE_ANDROID")
			.scopes("places", "events", "users", "transfer")
//			.resourceIds(resourceIds)
			.accessTokenValiditySeconds(36000000)
			.refreshTokenValiditySeconds(36000000)
			.secret(passwordEncoder.encode("secret"))
	.and()
		.withClient("register-app")
			.authorizedGrantTypes("client_credentials")
			.authorities("ROLE_REGISTER")
			.scopes("read")
//			.resourceIds(resourceIds)
			.secret(passwordEncoder.encode("secret"))
	.and()
		.withClient("client-redirect")
			.authorizedGrantTypes("authorization_code")
			.secret(passwordEncoder.encode("secret"))
			.scopes("places", "events", "transfer","users")
//			.resourceIds(resourceIds)
			.accessTokenValiditySeconds(36000000)
			.refreshTokenValiditySeconds(36000000)
			// .authorities(TwoFactorAuthenticationFilter.ROLE_TWO_FACTOR_AUTHENTICATION_ENABLED)
			.autoApprove(true);
	// .redirectUris("https://authclient:8443/me");

	}
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
		endpoints.tokenStore(tokenStore)
		        .accessTokenConverter(accessTokenConverter)
		        .tokenEnhancer(enhancerChain)
		        .authenticationManager(authenticationManager);
	}

}
