package com.sgarcia.bffgateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.server.ResponseStatusException;

import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;

@Configuration
public class FeignClientConfiguration {

	@Bean
	@ConfigurationProperties(prefix = "subcription.oauth2.client")
	public ClientCredentialsResourceDetails clientCredentialsResourceDetails() {
		return new ClientCredentialsResourceDetails();
	}

	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor() {
		return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
	}

	@Bean
	public CustomFeignErrorDecoder customDecoder() {
		return new CustomFeignErrorDecoder();
	}

	public class CustomFeignErrorDecoder implements ErrorDecoder {

		private final Logger log = LoggerFactory.getLogger(CustomFeignErrorDecoder.class);

		private ErrorDecoder delegate = new ErrorDecoder.Default();

		@Override
		public Exception decode(String methodKey, Response response) {

			log.trace("An exception has been caught in {}, trying to parse the playload.", methodKey);

			if (response.body() == null) {
				log.error("Failed to parse the playload: Response has no body.");
				return delegate.decode(methodKey, response);
			}

			final HttpStatus status = HttpStatus.valueOf(response.status());

			return new ResponseStatusException(status);

		}
	}

}
