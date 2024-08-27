package com.example.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
	{
		return builder.routes()
				.route("us-",r -> r.path("/users/user-get")
						.uri("lb://user-service"))
				
				.route("o-s",r -> r.path("/orders/order-get")
						.uri("lb://order-service"))
				
				
				.route("abcd",r -> r.path("/orders/getorderbyid/{id}")
						.uri("lb://order-service"))
				
				
				.route("ramu",r -> r.path("/users/getOrderByUserId/{id}")
						.uri("lb://user-service"))
				
				.build();
	}
}
