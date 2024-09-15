package com.example.microservices.user_service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/users")
public class UserController {

	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${service.env}")
	private String env;

	@GetMapping("/user-get")
	public String get() {
		return "User-Hello-Wrold "+env;
	}

	@GetMapping("/getOrderByUserId/{id}")
	@CircuitBreaker(name="user-circuit-breaker", fallbackMethod = "orderFallBack")
	public String getOrderByUserId(@PathVariable("id") String id) {
		String response = getOrderById(id);
		return response;
	}

	public String getOrderById(String orderId) {
		String url = "http://localhost:8080/orders/getorderbyid/" + orderId;
		return restTemplate.getForObject(url, String.class);
	}
	
	public String orderFallBack(Exception e)
	{
		return "Hey your order service is not working";
	}
}
