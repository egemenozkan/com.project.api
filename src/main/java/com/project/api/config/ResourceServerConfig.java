package com.project.api.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
//        .resourceId(resourceIds)
        .tokenServices(tokenServices);
    }

    @Override
	public void configure(HttpSecurity http) throws Exception {
    	// @formatter:off
		http.requestMatcher(new OAuthRequestedMatcher()).anonymous().disable().authorizeRequests()
//		.antMatchers(HttpMethod.OPTIONS).permitAll()
//		.antMatchers(HttpMethod.POST).permitAll()
//		.antMatchers(HttpMethod.GET).permitAll()
		.antMatchers("/api/test").permitAll()		
		.antMatchers("/api/datapool").permitAll()
				.antMatchers("/api/hello").access("hasAnyRole('USER')")
				.antMatchers("/api/me").hasAnyRole("USER", "ADMIN")
				//.access("#oauth2.hasScope('read') or (!#oauth2.isOAuth() and hasRole('ROLE_USER'))") 
				.antMatchers("/api/v1/places/**").access("#oauth2.hasScope('places')") 
				.antMatchers("/api/v1/events/**").access("#oauth2.hasScope('events')") 
				.antMatchers("/api/v1/users/**").access("#oauth2.hasScope('users')") 
				.antMatchers("/api/do").hasAuthority("ROLE_TRUSTED_CLIENT")
				.antMatchers("/api/register").hasAuthority("ROLE_REGISTER");
		// @formatter:on
	}

	private static class OAuthRequestedMatcher implements RequestMatcher {
		public boolean matches(HttpServletRequest request) {
			String auth = request.getHeader("Authorization");
			// Determine if the client request contained an OAuth Authorization
			boolean haveOauth2Token = (auth != null) && (auth.startsWith("Bearer") || auth.startsWith("bearer"));
			boolean haveAccessToken = request.getParameter("access_token") != null;
			return haveOauth2Token || haveAccessToken;
		}
	}
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//                http
//                .requestMatchers()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/actuator/**", "/api-docs/**").permitAll()
//                .antMatchers("/springjwt/**" ).authenticated();
//    }
}
