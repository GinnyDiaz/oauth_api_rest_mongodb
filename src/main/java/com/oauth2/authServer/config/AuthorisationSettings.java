package com.oauth2.authServer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "auth")
public class AuthorisationSettings {

    private String baseUrl;
    	
	private String authenticationUrl;

    private String checkTokenUrl;
    
    private String signUpUrl;
    
    private String resourcesUrl;
    
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
    
    public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public String getAuthenticationUrl() {
        return authenticationUrl;
    }

    public void setAuthenticationUrl(final String authenticationUrl) {
        this.authenticationUrl = authenticationUrl;
    }

    public String getCheckTokenUrl() {
        return checkTokenUrl;
    }

    public void setCheckTokenUrl(final String checkTokenUrl) {
        this.checkTokenUrl = checkTokenUrl;
    }
    
    public String getResourcesUrl() {
		return resourcesUrl;
	}

	public void setResourcesUrl(String resourcesUrl) {
		this.resourcesUrl = resourcesUrl;
	}


}