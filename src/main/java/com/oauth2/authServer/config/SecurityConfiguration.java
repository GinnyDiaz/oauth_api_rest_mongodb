package com.oauth2.authServer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import uk.co.caeldev.springsecuritymongo.MongoTokenStore;
import uk.co.caeldev.springsecuritymongo.MongoUserDetailsManager;


@Configuration
@Order(99)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
    private AuthorisationSettings authorizationSettings;
	
	@Autowired
    private MongoTokenStore mongoTokenStore; 

    @Autowired
    private MongoUserDetailsManager mongoUserDetailsService;
    
    @Autowired
    private PasswordEncoder encoder;
    
    
    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mongoUserDetailsService)
                .passwordEncoder(encoder);
    }
    
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers( authorizationSettings.getAuthenticationUrl()+"/**" ).permitAll()
        .antMatchers( authorizationSettings.getResourcesUrl()+"/**" ).permitAll()
        .antMatchers( authorizationSettings.getSignUpUrl() ).permitAll()
        .anyRequest().authenticated()
        .and().anonymous().disable();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers( HttpMethod.OPTIONS, "/**" );
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Bean
    public TokenStore tokenStore() {
        return mongoTokenStore;
    }
    
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}
