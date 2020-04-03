package com.oauth2.authServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
    private AuthorisationSettings authorizationSettings;
	
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
            .requestMatchers().antMatchers(authorizationSettings.getSignUpUrl(), authorizationSettings.getResourcesUrl()+"/**")
            .and()
            .authorizeRequests().antMatchers(authorizationSettings.getSignUpUrl()).permitAll()
            .and()
            .authorizeRequests().antMatchers(authorizationSettings.getResourcesUrl()+"/**").access("#oauth2.hasScope('read') or #oauth2.isClient()");
    }
}
