package com.sgarcia.bffgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BffGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BffGatewayApplication.class, args);
	}

}
