package com.sgarcia.subcription.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@EnableResourceServer
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.cors().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.authorizeRequests()
			.antMatchers("/swagger-ui.html", "/swagger-resources/**","/webjars/springfox-swagger-ui/**").permitAll()
			.antMatchers("/v2/api-docs").permitAll()
			.antMatchers("/api/subrciptions/**").access("#oauth2.hasScope('subcription_service')")
			.anyRequest().authenticated()
		.and()
			.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}