package com.jessica.apigateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}


	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()

		.route(p -> p
						.path("/products/**")
						.uri("http://localhost:8300/"))
				
						.route(p -> p
						.path("/categories/**")
						.filters(f -> f.circuitBreaker(config -> config
							.setFallbackUri("forward:/fallback")))
						.uri("http://localhost:8300/"))
			 .route(p -> p
			.path("/payments/**")
			.filters(f -> f.circuitBreaker(config -> config
				.setFallbackUri("forward:/fallback")))
			.uri("http://localhost:8200/"))

 
 
 
 .route(p -> p
			.path("/orders/**")
			.filters(f -> f.circuitBreaker(config -> config
				.setFallbackUri("forward:/fallback")))
			.uri("http://localhost:8100/"))
 
 
 .route(p -> p
			.path("/customers/**")
			.filters(f -> f.circuitBreaker(config -> config
				.setFallbackUri("forward:/fallback")))
			.uri("http://localhost:8000/"))
 


			.build();
}

@RequestMapping("/fallback")
public Mono<String> fallback() {
  return Mono.just("A problem has occured, please try again later.");
}

}