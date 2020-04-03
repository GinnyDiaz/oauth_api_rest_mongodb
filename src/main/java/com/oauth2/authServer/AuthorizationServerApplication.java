package com.oauth2.authServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import com.oauth2.authServer.config.EnableBaseAuthServer;


@SpringBootApplication
@EnableBaseAuthServer
@EnableAuthorizationServer
public class AuthorizationServerApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }
    
}
