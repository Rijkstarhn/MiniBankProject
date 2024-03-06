package com.eazybytes.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {


	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator ezbyteLocator (RouteLocatorBuilder builder) {
		return builder.routes()
				.route("account service",
						r -> r.path("/eazybank/accounts/**")
								.filters(f -> f.stripPrefix(2).addResponseHeader("responseTime", LocalDateTime.now().toString()))
								.uri("lb://ACCOUNTS"))
				.route("card service",
						r -> r.path("/eazybank/cards/**")
								.filters(f -> f.stripPrefix(2).addResponseHeader("responseTime", LocalDateTime.now().toString()))
								.uri("lb://CARDS"))
				.route("loan service",
						r -> r.path("/eazybank/loans/**")
								.filters(f -> f.stripPrefix(2).addResponseHeader("responseTime", LocalDateTime.now().toString()))
								.uri("lb://LOANS"))
				.build();
	}
}
